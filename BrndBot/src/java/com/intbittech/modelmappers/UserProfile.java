
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
    "facebookUrl",
    "address",
    "websiteUrl",
    "twitterUrl",
    "instagramUrl"
})
public class UserProfile {

    @JsonProperty("facebookUrl")
    private String facebookUrl;
    @JsonProperty("address")
    private String address;
    @JsonProperty("websiteUrl")
    private String websiteUrl;
    @JsonProperty("twitterUrl")
    private String twitterUrl;
    @JsonProperty("instagramUrl")
    private String instagramUrl;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The facebookUrl
     */
    @JsonProperty("facebookUrl")
    public String getFacebookUrl() {
        return facebookUrl;
    }

    /**
     * 
     * @param facebookUrl
     *     The facebookUrl
     */
    @JsonProperty("facebookUrl")
    public void setFacebookUrl(String facebookUrl) {
        this.facebookUrl = facebookUrl;
    }

    /**
     * 
     * @return
     *     The address
     */
    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    /**
     * 
     * @param address
     *     The address
     */
    @JsonProperty("address")
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 
     * @return
     *     The websiteUrl
     */
    @JsonProperty("websiteUrl")
    public String getWebsiteUrl() {
        return websiteUrl;
    }

    /**
     * 
     * @param websiteUrl
     *     The websiteUrl
     */
    @JsonProperty("websiteUrl")
    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    /**
     * 
     * @return
     *     The twitterUrl
     */
    @JsonProperty("twitterUrl")
    public String getTwitterUrl() {
        return twitterUrl;
    }

    /**
     * 
     * @param twitterUrl
     *     The twitterUrl
     */
    @JsonProperty("twitterUrl")
    public void setTwitterUrl(String twitterUrl) {
        this.twitterUrl = twitterUrl;
    }

    /**
     * 
     * @return
     *     The instagramUrl
     */
    @JsonProperty("instagramUrl")
    public String getInstagramUrl() {
        return instagramUrl;
    }

    /**
     * 
     * @param instagramUrl
     *     The instagramUrl
     */
    @JsonProperty("instagramUrl")
    public void setInstagramUrl(String instagramUrl) {
        this.instagramUrl = instagramUrl;
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
