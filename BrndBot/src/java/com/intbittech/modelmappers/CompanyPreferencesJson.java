
package com.intbittech.modelmappers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")

@JsonPropertyOrder({
    "emailSettings",
    "Twitter",
    "Facebook",
    "colors",
    "userProfile",
    "userProfileColor",
    "tooltips"
})
@Component
public class CompanyPreferencesJson {

    @JsonProperty("emailSettings")
    private EmailSettings emailSettings;
    @JsonProperty("Twitter")
    private Twitter twitter;
    @JsonProperty("Facebook")
    private Facebook facebook;
    @JsonProperty("colors")
    private List<String> colors = new ArrayList<String>();
    @JsonProperty("userProfile")
    private UserProfile userProfile;
    @JsonProperty("userProfileColor")
    private String userProfileColor;
    @JsonProperty("tooltips")
    private Tooltips tooltips;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The emailSettings
     */
    @JsonProperty("emailSettings")
    public EmailSettings getEmailSettings() {
        return emailSettings;
    }

    /**
     * 
     * @param emailSettings
     *     The emailSettings
     */
    @JsonProperty("emailSettings")
    public void setEmailSettings(EmailSettings emailSettings) {
        this.emailSettings = emailSettings;
    }

    /**
     * 
     * @return
     *     The twitter
     */
    @JsonProperty("Twitter")
    public Twitter getTwitter() {
        return twitter;
    }

    /**
     * 
     * @param twitter
     *     The Twitter
     */
    @JsonProperty("Twitter")
    public void setTwitter(Twitter twitter) {
        this.twitter = twitter;
    }

    /**
     * 
     * @return
     *     The facebook
     */
    @JsonProperty("Facebook")
    public Facebook getFacebook() {
        return facebook;
    }

    /**
     * 
     * @param facebook
     *     The Facebook
     */
    @JsonProperty("Facebook")
    public void setFacebook(Facebook facebook) {
        this.facebook = facebook;
    }

    /**
     * 
     * @return
     *     The colors
     */
    @JsonProperty("colors")
    public List<String> getColors() {
        return colors;
    }

    /**
     * 
     * @param colors
     *     The colors
     */
    @JsonProperty("colors")
    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    /**
     * 
     * @return
     *     The userProfile
     */
    @JsonProperty("userProfile")
    public UserProfile getUserProfile() {
        return userProfile;
    }

    /**
     * 
     * @param userProfile
     *     The userProfile
     */
    @JsonProperty("userProfile")
    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }

    /**
     * 
     * @return
     *     The userProfileColor
     */
    @JsonProperty("userProfileColor")
    public String getUserProfileColor() {
        return userProfileColor;
    }

    /**
     * 
     * @param userProfileColor
     *     The userProfileColor
     */
    @JsonProperty("userProfileColor")
    public void setUserProfileColor(String userProfileColor) {
        this.userProfileColor = userProfileColor;
    }

    /**
     * 
     * @return
     *     The tooltips
     */
    @JsonProperty("tooltips")
    public Tooltips getTooltips() {
        return tooltips;
    }

    /**
     * 
     * @param tooltips
     *     The tooltips
     */
    @JsonProperty("tooltips")
    public void setTooltips(Tooltips tooltips) {
        this.tooltips = tooltips;
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
