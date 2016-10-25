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
    "companyProfile",
    "onBoarduserProfileing"
})
@Component
public class CompanyPreferencesJson {

    @JsonProperty("emailSettings")
    private EmailSettings emailSettings;
    @JsonProperty("Twitter")
    private TwitterDataDetails twitter;
    @JsonProperty("Facebook")
    private FacebookDataDetails facebook;
    @JsonProperty("colors")
    private List<String> colors = new ArrayList<String>();
    @JsonProperty("companyProfile")
    private CompanyProfile companyProfile;
    @JsonProperty("onBoarding")
    private OnBoarding onBoarding;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return The emailSettings
     */
    @JsonProperty("emailSettings")
    public EmailSettings getEmailSettings() {
        return emailSettings;
    }

    /**
     *
     * @param emailSettings The emailSettings
     */
    @JsonProperty("emailSettings")
    public void setEmailSettings(EmailSettings emailSettings) {
        this.emailSettings = emailSettings;
    }

    /**
     *
     * @return The twitter
     */
    @JsonProperty("Twitter")
    public TwitterDataDetails getTwitter() {
        return twitter;
    }

    /**
     *
     * @param twitter The Twitter
     */
    @JsonProperty("Twitter")
    public void setTwitter(TwitterDataDetails twitter) {
        this.twitter = twitter;
    }

    /**
     *
     * @return The facebook
     */
    @JsonProperty("Facebook")
    public FacebookDataDetails getFacebook() {
        return facebook;
    }

    /**
     *
     * @param facebook The Facebook
     */
    @JsonProperty("Facebook")
    public void setFacebook(FacebookDataDetails facebook) {
        this.facebook = facebook;
    }

    /**
     *
     * @return The colors
     */
    @JsonProperty("colors")
    public List<String> getColors() {
        return colors;
    }

    /**
     *
     * @param colors The colors
     */
    @JsonProperty("colors")
    public void setColors(List<String> colors) {
        this.colors = colors;
    }

    /**
     *
     * @return The companyProfile
     */
    @JsonProperty("companyProfile")
    public CompanyProfile getCompanyProfile() {
        return companyProfile;
    }

    /**
     * 
     * @param companyProfile
     *     The companyProfile
     */
    @JsonProperty("companyProfile")
    public void setCompanyProfile(CompanyProfile companyProfile) {
        this.companyProfile = companyProfile;
    }
    /**
     * 
     * @return
     *     The onBoarding
     */
    @JsonProperty("onBoarding")
    public OnBoarding getOnBoarding() {
        return onBoarding;
    }

    /**
     * 
     * @param onBoarding
     *     The onBoarding
     */
    @JsonProperty("onBoarding")
    public void setOnBoarding(OnBoarding onBoarding) {
        this.onBoarding = onBoarding;
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