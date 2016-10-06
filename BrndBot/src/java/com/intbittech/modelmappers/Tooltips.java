
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
    "CriticalInfoTips",
    "TutorialTip",
    "OrientationTips"
})
public class Tooltips {

    @JsonProperty("CriticalInfoTips")
    private CriticalInfoTips criticalInfoTips;
    @JsonProperty("TutorialTip")
    private TutorialTip tutorialTip;
    @JsonProperty("OrientationTips")
    private OrientationTips orientationTips;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The criticalInfoTips
     */
    @JsonProperty("CriticalInfoTips")
    public CriticalInfoTips getCriticalInfoTips() {
        return criticalInfoTips;
    }

    /**
     * 
     * @param criticalInfoTips
     *     The CriticalInfoTips
     */
    @JsonProperty("CriticalInfoTips")
    public void setCriticalInfoTips(CriticalInfoTips criticalInfoTips) {
        this.criticalInfoTips = criticalInfoTips;
    }

    /**
     * 
     * @return
     *     The tutorialTip
     */
    @JsonProperty("TutorialTip")
    public TutorialTip getTutorialTip() {
        return tutorialTip;
    }

    /**
     * 
     * @param tutorialTip
     *     The TutorialTip
     */
    @JsonProperty("TutorialTip")
    public void setTutorialTip(TutorialTip tutorialTip) {
        this.tutorialTip = tutorialTip;
    }

    /**
     * 
     * @return
     *     The orientationTips
     */
    @JsonProperty("OrientationTips")
    public OrientationTips getOrientationTips() {
        return orientationTips;
    }

    /**
     * 
     * @param orientationTips
     *     The OrientationTips
     */
    @JsonProperty("OrientationTips")
    public void setOrientationTips(OrientationTips orientationTips) {
        this.orientationTips = orientationTips;
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
