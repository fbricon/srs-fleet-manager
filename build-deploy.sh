#!/bin/bash

set -eo pipefail

# The version should be the short hash from git. This is what the deployent process expects.
# used for IMAGE_TAG template parameter that app-interface automatically generates
VERSION="$(git log --pretty=format:'%h' -n 1)"

PROJECT_NAME="srs-fleet-manager"

IMAGE_REGISTRY="quay.io"
IMAGE_ORG="rhoas"
IMAGE_NAME="${PROJECT_NAME}"
IMAGE_TAG="${VERSION}"


DOCKER_BUILD_COMMAND="docker build -f ./core/src/main/docker/Dockerfile.legacy-jar -t ${IMAGE_REGISTRY}/${IMAGE_ORG}/${IMAGE_NAME}:${IMAGE_TAG} ./core/"





display_usage() {
    cat <<EOT


##########################################################################################################################

 This script gets triggered by the automated CI/CD jobs of AppSRE. It builds and pushes '${PROJECT_NAME}' image to the
 'rhoas' organization in 'quay.io' registry(defaults). Quay-organization, Image name and tags are configurable.

 In order to work, it needs the following variables defined in the CI/CD configuration of the project:

 RHOAS_QUAY_USER - The name of the robot account
                   used to push images to 'quay.io'

 RHOAS_QUAY_TOKEN - The token of the robot account
                    used to push images to 'quay.io'

 The machines that run this script need to have access to internet, so that the built images can be pushed.


 Usage: $0 [options]
 Example: $0 --version 2.0.0.Final --org rhoas --name srs-fleet-manager --tag 2.0.0.Final
 
 options include:
 
 -v, --version     Version of apicurio-registry-tenant-manager-client. If not set defaults to '2.0.0.Final'
 -o, --org         The organization the container image will be part of. If not set defaults to 'rhoas'
 -n, --name        The name of the container image. If not set defaults to 'srs-fleet-manager'
 -t, --tag         The tag of the container image. If not set defaults to 'latest'
 -h, --help        This help message

##########################################################################################################################


EOT
}

build_project() {
    echo "#######################################################################################################"
    echo " Building Project '${PROJECT_NAME}'..."
    echo "#######################################################################################################"
    # AppSRE environments doesn't have maven and jdk11 which are required dependencies for building this project
    # Installing these dependencies is a tedious task and also since it's a shared instance, installing the required versions of these dependencies is not possible sometimes
    # Hence, using custom container that packs the required dependencies with the specific required versions
    # docker run --rm -t -u $(id -u):$(id -g) -v $(pwd):/home/user --workdir /home/user quay.io/riprasad/srs-project-builder:latest bash -c "${MVN_BUILD_COMMAND}"

    docker pull quay.io/app-sre/mk-ci-tools:latest
    docker run -v $(pwd):/opt/srs -w /opt/srs -e HOME=/tmp -u $(id -u) \
        -e APICURIO_REGISTRY_REPO=https://gitlab.cee.redhat.com/service-registry/srs-service-registry.git \
        -e APICURIO_REGISTRY_BRANCH=master \
        quay.io/app-sre/mk-ci-tools:latest make build-project
}


build_image() {
    echo "#######################################################################################################"
    echo " Building Image ${IMAGE_REGISTRY}/${IMAGE_ORG}/${IMAGE_NAME}:${IMAGE_TAG}"
    echo " IMAGE_REGISTRY: ${IMAGE_REGISTRY}"
    echo " IMAGE_ORG: ${IMAGE_ORG}"
    echo " IMAGE_NAME: ${IMAGE_NAME}"
    echo " IMAGE_TAG: ${IMAGE_TAG}"
    echo " Build Command: ${DOCKER_BUILD_COMMAND}"
    echo "#######################################################################################################"
    ${DOCKER_BUILD_COMMAND}
}


push_image() {
    echo "Logging to ${IMAGE_REGISTRY}..."
    echo "docker login -u ${RHOAS_QUAY_USER} -p ${RHOAS_QUAY_TOKEN} ${IMAGE_REGISTRY}"
    docker login -u "${RHOAS_QUAY_USER}" -p "${RHOAS_QUAY_TOKEN}" "${IMAGE_REGISTRY}"
    if [ $? -eq 0 ]
    then
      echo "Login to ${IMAGE_REGISTRY} Succeeded!"
    else
      echo "Login to ${IMAGE_REGISTRY} Failed!"
    fi

    echo "#######################################################################################################"
    echo " Pushing Image ${IMAGE_REGISTRY}/${IMAGE_ORG}/${IMAGE_NAME}:${IMAGE_TAG}"
    echo "#######################################################################################################"
    docker push "${IMAGE_REGISTRY}/${IMAGE_ORG}/${IMAGE_NAME}:${IMAGE_TAG}"
    docker tag "${IMAGE_REGISTRY}/${IMAGE_ORG}/${IMAGE_NAME}:${IMAGE_TAG}" "${IMAGE_REGISTRY}/${IMAGE_ORG}/${IMAGE_NAME}:latest"
    docker push "${IMAGE_REGISTRY}/${IMAGE_ORG}/${IMAGE_NAME}:latest"
    if [ $? -eq 0 ]
    then
      echo "Image successfully pushed to ${IMAGE_REGISTRY}"
    else
      echo "Image Push Failed!"
    fi
}






main() { 

    # Parse command line arguments
    while [ $# -gt 0 ]
    do
        arg="$1"

        case $arg in
          -h|--help)
            shift
            display_usage
            exit 0
            ;;
          -o|--org)
            shift
            IMAGE_ORG="$1"
            ;;
          -n|--name)
            shift
            IMAGE_NAME="$1"
            ;;
          -t|--tag)
            shift
            IMAGE_TAG="$1"
            ;;
          *)
            echo "Unknown argument: $1"
            display_usage
            exit 1
            ;;
        esac
        shift
    done
    
    # The credentials to quay.io will be provided during pipeline runtime and you should make sure that following environment variables are available
    if [[ ! -z "${RHOAS_QUAY_USER}" ]] && [[ ! -z "${RHOAS_QUAY_TOKEN}" ]]; then
       echo "==| RHOAS Quay.io user and token is set, will push images to RHOAS org |=="
    else
       echo "RHOAS Quay.io user and token is not set. Aborting the process..."
       exit 1
    fi


    # function calls
    build_project
    build_image
    push_image

}

main $*
