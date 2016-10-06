
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
    "valid",
    "reason"
})
public class Dkim2 {

    @JsonProperty("valid")
    private Boolean valid;
    @JsonProperty("reason")
    private Object reason;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The valid
     */
    @JsonProperty("valid")
    public Boolean getValid() {
        return valid;
    }

    /**
     * 
     * @param valid
     *     The valid
     */
    @JsonProperty("valid")
    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    public Dkim2 withValid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    /**
     * 
     * @return
     *     The reason
     */
    @JsonProperty("reason")
    public Object getReason() {
        return reason;
    }

    /**
     * 
     * @param reason
     *     The reason
     */
    @JsonProperty("reason")
    public void setReason(Object reason) {
        this.reason = reason;
    }

    public Dkim2 withReason(Object reason) {
        this.reason = reason;
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

    public Dkim2 withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
