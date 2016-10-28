
package com.intbittech.sendgrid.models;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "sendGridStats"
})
public class SendGridStatsList extends BaseSendGridModel{

    @JsonProperty("sendGridStats")
    private List<SendGridStats> sendGridStats = new ArrayList<SendGridStats>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The sendGridStats
     */
    @JsonProperty("sendGridStats")
    public List<SendGridStats> getSendGridStats() {
        return sendGridStats;
    }

    /**
     * 
     * @param sendGridStats
     *     The sendGridStats
     */
    @JsonProperty("sendGridStats")
    public void setSendGridStats(List<SendGridStats> sendGridStats) {
        this.sendGridStats = sendGridStats;
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