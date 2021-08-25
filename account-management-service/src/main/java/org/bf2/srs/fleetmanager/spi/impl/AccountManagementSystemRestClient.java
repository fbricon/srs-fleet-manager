package org.bf2.srs.fleetmanager.spi.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.apicurio.rest.client.JdkHttpClient;
import io.apicurio.rest.client.auth.OidcAuth;
import io.apicurio.rest.client.request.Operation;
import io.apicurio.rest.client.request.Request;
import io.apicurio.rest.client.spi.ApicurioHttpClient;
import org.bf2.srs.fleetmanager.spi.impl.exception.AccountManagementErrorHandler;
import org.bf2.srs.fleetmanager.spi.impl.exception.AccountManagementSystemClientException;
import org.bf2.srs.fleetmanager.spi.impl.model.request.ClusterAuthorization;
import org.bf2.srs.fleetmanager.spi.impl.model.request.TermsReview;
import org.bf2.srs.fleetmanager.spi.impl.model.response.ResponseTermsReview;
import org.bf2.srs.fleetmanager.spi.impl.model.response.ClusterAuthorizationResponse;

import java.util.Collections;
import java.util.Map;

public class AccountManagementSystemRestClient {

    private final ApicurioHttpClient client;
    private final ObjectMapper mapper;

    public AccountManagementSystemRestClient(String endpoint, Map<String, Object> configs, OidcAuth auth) {
        this.client = new JdkHttpClient(endpoint, configs, auth, new AccountManagementErrorHandler());
        this.mapper = new ObjectMapper();
    }

    public ResponseTermsReview termsReview(TermsReview termsReview) {
        try {
            return this.client.sendRequest(new Request.RequestBuilder<ResponseTermsReview>()
                    .operation(Operation.POST)
                    .path(Paths.TERMS_REVIEW_PATH)
                    .data(mapper.writeValueAsString(termsReview))
                    .responseType(new TypeReference<ResponseTermsReview>() {
                    })
                    .build());
        } catch (JsonProcessingException e) {
            throw new AccountManagementSystemClientException(e);
        }
    }

    public ClusterAuthorizationResponse clusterAuthorization(ClusterAuthorization clusterAuthorization) {
        try {
            return this.client.sendRequest(new Request.RequestBuilder<ClusterAuthorizationResponse>()
                    .operation(Operation.POST)
                    .path(Paths.CLUSTER_AUTHORIZATION)
                    .data(mapper.writeValueAsString(clusterAuthorization))
                    .responseType(new TypeReference<ClusterAuthorizationResponse>() {
                    })
                    .build());
        } catch (JsonProcessingException e) {
            throw new AccountManagementSystemClientException(e);
        }
    }

    public void deleteSubscription(String subscriptionId) {
        this.client.sendRequest(new Request.RequestBuilder<Void>()
                .operation(Operation.DELETE)
                .path(Paths.SUBSCRIPTIONS)
                .pathParams(Collections.singletonList(subscriptionId))
                .responseType(new TypeReference<Void>() {})
                .build());
    }
}