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
import org.springframework.stereotype.Component;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "profileColor",
    "tooltips"
})
@Component
public class UserPreferencesJson {

    @JsonProperty("profileColor")
    private String profileColor;
    @JsonProperty("tooltips")
    private Tooltips tooltips;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     *
     * @return The profileColor
     */
    @JsonProperty("profileColor")
    public String getProfileColor() {
        return profileColor;
    }

    /**
     *
     * @param profileColor The profileColor
     */
    @JsonProperty("profileColor")
    public void setProfileColor(String profileColor) {
        this.profileColor = profileColor;
    }

    /**
     *
     * @return The tooltips
     */
    @JsonProperty("tooltips")
    public Tooltips getTooltips() {
        return tooltips;
    }

    /**
     *
     * @param tooltips The tooltips
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
