
package com.intbittech.modelmappers;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "twitter_access_token_secret",
    "TwitterLoggedIn",
    "twitter_access_token",
    "twitter_user_name"
})
public class TwitterDataDetails {

    @JsonProperty("twitter_access_token_secret")
    private String twitterAccessTokenSecret;
    @JsonProperty("TwitterLoggedIn")
    private String twitterLoggedIn;
    @JsonProperty("twitter_access_token")
    private String twitterAccessToken;
    @JsonProperty("twitter_user_name")
    private String twitterUserName;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The twitterAccessTokenSecret
     */
    @JsonProperty("twitter_access_token_secret")
    public String getTwitterAccessTokenSecret() {
        return twitterAccessTokenSecret;
    }

    /**
     * 
     * @param twitterAccessTokenSecret
     *     The twitter_access_token_secret
     */
    @JsonProperty("twitter_access_token_secret")
    public void setTwitterAccessTokenSecret(String twitterAccessTokenSecret) {
        this.twitterAccessTokenSecret = twitterAccessTokenSecret;
    }

    /**
     * 
     * @return
     *     The twitterLoggedIn
     */
    @JsonProperty("TwitterLoggedIn")
    public String getTwitterLoggedIn() {
        return twitterLoggedIn;
    }

    /**
     * 
     * @param twitterLoggedIn
     *     The TwitterLoggedIn
     */
    @JsonProperty("TwitterLoggedIn")
    public void setTwitterLoggedIn(String twitterLoggedIn) {
        this.twitterLoggedIn = twitterLoggedIn;
    }

    /**
     * 
     * @return
     *     The twitterAccessToken
     */
    @JsonProperty("twitter_access_token")
    public String getTwitterAccessToken() {
        return twitterAccessToken;
    }

    /**
     * 
     * @param twitterAccessToken
     *     The twitter_access_token
     */
    @JsonProperty("twitter_access_token")
    public void setTwitterAccessToken(String twitterAccessToken) {
        this.twitterAccessToken = twitterAccessToken;
    }

    /**
     * 
     * @return
     *     The twitterUserName
     */
    @JsonProperty("twitter_user_name")
    public String getTwitterUserName() {
        return twitterUserName;
    }

    /**
     * 
     * @param twitterUserName
     *     The twitter_user_name
     */
    @JsonProperty("twitter_user_name")
    public void setTwitterUserName(String twitterUserName) {
        this.twitterUserName = twitterUserName;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
