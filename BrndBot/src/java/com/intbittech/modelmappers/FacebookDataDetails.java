
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
    "fb_user_profile_name",
    "FacebookLoggedIn",
    "fb_default_page_name",
    "fb_default_page_access_token"
})
public class FacebookDataDetails {

    @JsonProperty("fb_user_profile_name")
    private String fbUserProfileName;
    @JsonProperty("FacebookLoggedIn")
    private String facebookLoggedIn;
    @JsonProperty("fb_default_page_name")
    private String fbDefaultPageName;
    @JsonProperty("fb_default_page_access_token")
    private String fbDefaultPageAccessToken;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The fbUserProfileName
     */
    @JsonProperty("fb_user_profile_name")
    public String getFbUserProfileName() {
        return fbUserProfileName;
    }

    /**
     * 
     * @param fbUserProfileName
     *     The fb_user_profile_name
     */
    @JsonProperty("fb_user_profile_name")
    public void setFbUserProfileName(String fbUserProfileName) {
        this.fbUserProfileName = fbUserProfileName;
    }

    /**
     * 
     * @return
     *     The facebookLoggedIn
     */
    @JsonProperty("FacebookLoggedIn")
    public String getFacebookLoggedIn() {
        return facebookLoggedIn;
    }

    /**
     * 
     * @param facebookLoggedIn
     *     The FacebookLoggedIn
     */
    @JsonProperty("FacebookLoggedIn")
    public void setFacebookLoggedIn(String facebookLoggedIn) {
        this.facebookLoggedIn = facebookLoggedIn;
    }

    /**
     * 
     * @return
     *     The fbDefaultPageName
     */
    @JsonProperty("fb_default_page_name")
    public String getFbDefaultPageName() {
        return fbDefaultPageName;
    }

    /**
     * 
     * @param fbDefaultPageName
     *     The fb_default_page_name
     */
    @JsonProperty("fb_default_page_name")
    public void setFbDefaultPageName(String fbDefaultPageName) {
        this.fbDefaultPageName = fbDefaultPageName;
    }

    /**
     * 
     * @return
     *     The fbDefaultPageAccessToken
     */
    @JsonProperty("fb_default_page_access_token")
    public String getFbDefaultPageAccessToken() {
        return fbDefaultPageAccessToken;
    }

    /**
     * 
     * @param fbDefaultPageAccessToken
     *     The fb_default_page_access_token
     */
    @JsonProperty("fb_default_page_access_token")
    public void setFbDefaultPageAccessToken(String fbDefaultPageAccessToken) {
        this.fbDefaultPageAccessToken = fbDefaultPageAccessToken;
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
