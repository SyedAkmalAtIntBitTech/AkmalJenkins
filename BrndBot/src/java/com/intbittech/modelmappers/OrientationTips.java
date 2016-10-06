
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
    "unsubscribedEmail"
})
public class OrientationTips {

    @JsonProperty("unsubscribedEmail")
    private Boolean unsubscribedEmail;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The unsubscribedEmail
     */
    @JsonProperty("unsubscribedEmail")
    public Boolean getUnsubscribedEmail() {
        return unsubscribedEmail;
    }

    /**
     * 
     * @param unsubscribedEmail
     *     The unsubscribedEmail
     */
    @JsonProperty("unsubscribedEmail")
    public void setUnsubscribedEmail(Boolean unsubscribedEmail) {
        this.unsubscribedEmail = unsubscribedEmail;
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
