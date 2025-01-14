
package org.bf2.srs.fleetmanager.rest.publicapi.beans;

import java.util.Date;

import javax.annotation.processing.Generated;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * Root Type for Registry
 * <p>
 * Service Registry instance within a multi-tenant deployment.
 *
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "kind",
    "href",
    "status",
    "registryUrl",
    "browserUrl",
    "name",
    "registryDeploymentId",
    "owner",
    "description",
    "created_at",
    "updated_at",
    "instance_type"
})
@Generated("jsonschema2pojo")
public class Registry {

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("id")
    private String id;
    @JsonProperty("kind")
    private String kind;
    @JsonProperty("href")
    private String href;
    /**
     * "accepted": Registry status when accepted for processing.
     *
     * "provisioning": Registry status when provisioning a new instance.
     *
     * "ready": Registry status when ready for use.
     *
     * "failed": Registry status when the provisioning failed. When removing a Registry in this state,
     * the status transitions directly to "deleting".
     *
     *
     * "deprovision": Registry status when accepted for deprovisioning.
     *
     * "deleting": Registry status when deprovisioning.
     *
     * (Required)
     *
     */
    @JsonProperty("status")
    @JsonPropertyDescription("\"accepted\": Registry status when accepted for processing.\n\n\"provisioning\": Registry status when provisioning a new instance.\n\n\"ready\": Registry status when ready for use.\n\n\"failed\": Registry status when the provisioning failed. When removing a Registry in this state,\nthe status transitions directly to \"deleting\".\n\n\n\"deprovision\": Registry status when accepted for deprovisioning.\n\n\"deleting\": Registry status when deprovisioning.\n")
    private RegistryStatusValue status;
    @JsonProperty("registryUrl")
    private String registryUrl;
    @JsonProperty("browserUrl")
    private String browserUrl;
    /**
     * User-defined Registry name. Does not have to be unique.
     *
     */
    @JsonProperty("name")
    @JsonPropertyDescription("User-defined Registry name. Does not have to be unique.")
    private String name;
    /**
     * Identifier of a multi-tenant deployment, where this Service Registry instance resides.
     *
     */
    @JsonProperty("registryDeploymentId")
    @JsonPropertyDescription("Identifier of a multi-tenant deployment, where this Service Registry instance resides.")
    private Integer registryDeploymentId;
    /**
     * Registry instance owner
     *
     */
    @JsonProperty("owner")
    @JsonPropertyDescription("Registry instance owner")
    private String owner;
    /**
     * Description of the Registry instance.
     *
     */
    @JsonProperty("description")
    @JsonPropertyDescription("Description of the Registry instance.")
    private String description;
    /**
     * ISO 8601 UTC timestamp.
     * (Required)
     *
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @JsonProperty("created_at")
    @JsonPropertyDescription("ISO 8601 UTC timestamp.")
    private Date createdAt;
    /**
     * ISO 8601 UTC timestamp.
     * (Required)
     *
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    @JsonProperty("updated_at")
    @JsonPropertyDescription("ISO 8601 UTC timestamp.")
    private Date updatedAt;
    /**
     * "standard": Standard, full-featured Registry instance
     *
     * "eval": Evaluation (Trial) instance, provided for a limited time
     *
     * (Required)
     *
     */
    @JsonProperty("instance_type")
    @JsonPropertyDescription("\"standard\": Standard, full-featured Registry instance\n\n\"eval\": Evaluation (Trial) instance, provided for a limited time\n")
    private RegistryInstanceTypeValue instanceType;

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * (Required)
     *
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("kind")
    public String getKind() {
        return kind;
    }

    @JsonProperty("kind")
    public void setKind(String kind) {
        this.kind = kind;
    }

    @JsonProperty("href")
    public String getHref() {
        return href;
    }

    @JsonProperty("href")
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * "accepted": Registry status when accepted for processing.
     *
     * "provisioning": Registry status when provisioning a new instance.
     *
     * "ready": Registry status when ready for use.
     *
     * "failed": Registry status when the provisioning failed. When removing a Registry in this state,
     * the status transitions directly to "deleting".
     *
     *
     * "deprovision": Registry status when accepted for deprovisioning.
     *
     * "deleting": Registry status when deprovisioning.
     *
     * (Required)
     *
     */
    @JsonProperty("status")
    public RegistryStatusValue getStatus() {
        return status;
    }

    /**
     * "accepted": Registry status when accepted for processing.
     *
     * "provisioning": Registry status when provisioning a new instance.
     *
     * "ready": Registry status when ready for use.
     *
     * "failed": Registry status when the provisioning failed. When removing a Registry in this state,
     * the status transitions directly to "deleting".
     *
     *
     * "deprovision": Registry status when accepted for deprovisioning.
     *
     * "deleting": Registry status when deprovisioning.
     *
     * (Required)
     *
     */
    @JsonProperty("status")
    public void setStatus(RegistryStatusValue status) {
        this.status = status;
    }

    @JsonProperty("registryUrl")
    public String getRegistryUrl() {
        return registryUrl;
    }

    @JsonProperty("registryUrl")
    public void setRegistryUrl(String registryUrl) {
        this.registryUrl = registryUrl;
    }

    @JsonProperty("browserUrl")
    public String getBrowserUrl() {
        return browserUrl;
    }

    @JsonProperty("browserUrl")
    public void setBrowserUrl(String browserUrl) {
        this.browserUrl = browserUrl;
    }

    /**
     * User-defined Registry name. Does not have to be unique.
     *
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * User-defined Registry name. Does not have to be unique.
     *
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Identifier of a multi-tenant deployment, where this Service Registry instance resides.
     *
     */
    @JsonProperty("registryDeploymentId")
    public Integer getRegistryDeploymentId() {
        return registryDeploymentId;
    }

    /**
     * Identifier of a multi-tenant deployment, where this Service Registry instance resides.
     *
     */
    @JsonProperty("registryDeploymentId")
    public void setRegistryDeploymentId(Integer registryDeploymentId) {
        this.registryDeploymentId = registryDeploymentId;
    }

    /**
     * Registry instance owner
     *
     */
    @JsonProperty("owner")
    public String getOwner() {
        return owner;
    }

    /**
     * Registry instance owner
     *
     */
    @JsonProperty("owner")
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Description of the Registry instance.
     *
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * Description of the Registry instance.
     *
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ISO 8601 UTC timestamp.
     * (Required)
     *
     */
    @JsonProperty("created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * ISO 8601 UTC timestamp.
     * (Required)
     *
     */
    @JsonProperty("created_at")
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * ISO 8601 UTC timestamp.
     * (Required)
     *
     */
    @JsonProperty("updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * ISO 8601 UTC timestamp.
     * (Required)
     *
     */
    @JsonProperty("updated_at")
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * "standard": Standard, full-featured Registry instance
     *
     * "eval": Evaluation (Trial) instance, provided for a limited time
     *
     * (Required)
     *
     */
    @JsonProperty("instance_type")
    public RegistryInstanceTypeValue getInstanceType() {
        return instanceType;
    }

    /**
     * "standard": Standard, full-featured Registry instance
     *
     * "eval": Evaluation (Trial) instance, provided for a limited time
     *
     * (Required)
     *
     */
    @JsonProperty("instance_type")
    public void setInstanceType(RegistryInstanceTypeValue instanceType) {
        this.instanceType = instanceType;
    }

}
