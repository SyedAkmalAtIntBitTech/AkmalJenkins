
package com.intbittech.sendgrid.models;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "username",
    "user_id",
    "email",
    "signup_session_token",
    "authorization_token",
    "credit_allocation"
})
public class SendGridUser extends BaseSendGridModel {

    @JsonProperty("username")
    private String username;
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("email")
    private String email;
    @JsonProperty("signup_session_token")
    private String signupSessionToken;
    @JsonProperty("authorization_token")
    private String authorizationToken;
    @JsonProperty("credit_allocation")
    private CreditAllocation creditAllocation;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The username
     */
    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    /**
     * 
     * @param username
     *     The username
     */
    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    public SendGridUser withUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * 
     * @return
     *     The userId
     */
    @JsonProperty("user_id")
    public Integer getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     *     The user_id
     */
    @JsonProperty("user_id")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public SendGridUser withUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    /**
     * 
     * @return
     *     The email
     */
    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     *     The email
     */
    @JsonProperty("email")
    public void setEmail(String email) {
        this.email = email;
    }

    public SendGridUser withEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * 
     * @return
     *     The signupSessionToken
     */
    @JsonProperty("signup_session_token")
    public String getSignupSessionToken() {
        return signupSessionToken;
    }

    /**
     * 
     * @param signupSessionToken
     *     The signup_session_token
     */
    @JsonProperty("signup_session_token")
    public void setSignupSessionToken(String signupSessionToken) {
        this.signupSessionToken = signupSessionToken;
    }

    public SendGridUser withSignupSessionToken(String signupSessionToken) {
        this.signupSessionToken = signupSessionToken;
        return this;
    }

    /**
     * 
     * @return
     *     The authorizationToken
     */
    @JsonProperty("authorization_token")
    public String getAuthorizationToken() {
        return authorizationToken;
    }

    /**
     * 
     * @param authorizationToken
     *     The authorization_token
     */
    @JsonProperty("authorization_token")
    public void setAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
    }

    public SendGridUser withAuthorizationToken(String authorizationToken) {
        this.authorizationToken = authorizationToken;
        return this;
    }

    /**
     * 
     * @return
     *     The creditAllocation
     */
    @JsonProperty("credit_allocation")
    public CreditAllocation getCreditAllocation() {
        return creditAllocation;
    }

    /**
     * 
     * @param creditAllocation
     *     The credit_allocation
     */
    @JsonProperty("credit_allocation")
    public void setCreditAllocation(CreditAllocation creditAllocation) {
        this.creditAllocation = creditAllocation;
    }

    public SendGridUser withCreditAllocation(CreditAllocation creditAllocation) {
        this.creditAllocation = creditAllocation;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public SendGridUser withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
