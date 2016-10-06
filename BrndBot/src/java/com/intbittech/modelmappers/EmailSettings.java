
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
    "reply_email_address",
    "from_name",
    "from_address"
})
public class EmailSettings {

    @JsonProperty("reply_email_address")
    private String replyEmailAddress;
    @JsonProperty("from_name")
    private String fromName;
    @JsonProperty("from_address")
    private String fromAddress;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The replyEmailAddress
     */
    @JsonProperty("reply_email_address")
    public String getReplyEmailAddress() {
        return replyEmailAddress;
    }

    /**
     * 
     * @param replyEmailAddress
     *     The reply_email_address
     */
    @JsonProperty("reply_email_address")
    public void setReplyEmailAddress(String replyEmailAddress) {
        this.replyEmailAddress = replyEmailAddress;
    }

    /**
     * 
     * @return
     *     The fromName
     */
    @JsonProperty("from_name")
    public String getFromName() {
        return fromName;
    }

    /**
     * 
     * @param fromName
     *     The from_name
     */
    @JsonProperty("from_name")
    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    /**
     * 
     * @return
     *     The fromAddress
     */
    @JsonProperty("from_address")
    public String getFromAddress() {
        return fromAddress;
    }

    /**
     * 
     * @param fromAddress
     *     The from_address
     */
    @JsonProperty("from_address")
    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
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
