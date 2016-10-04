
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
    "mail_cname",
    "dkim1",
    "dkim2"
})
public class ValidationResults {

    @JsonProperty("mail_cname")
    private MailCname mailCname;
    @JsonProperty("dkim1")
    private Dkim1 dkim1;
    @JsonProperty("dkim2")
    private Dkim2 dkim2;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The mailCname
     */
    @JsonProperty("mail_cname")
    public MailCname getMailCname() {
        return mailCname;
    }

    /**
     * 
     * @param mailCname
     *     The mail_cname
     */
    @JsonProperty("mail_cname")
    public void setMailCname(MailCname mailCname) {
        this.mailCname = mailCname;
    }

    public ValidationResults withMailCname(MailCname mailCname) {
        this.mailCname = mailCname;
        return this;
    }

    /**
     * 
     * @return
     *     The dkim1
     */
    @JsonProperty("dkim1")
    public Dkim1 getDkim1() {
        return dkim1;
    }

    /**
     * 
     * @param dkim1
     *     The dkim1
     */
    @JsonProperty("dkim1")
    public void setDkim1(Dkim1 dkim1) {
        this.dkim1 = dkim1;
    }

    public ValidationResults withDkim1(Dkim1 dkim1) {
        this.dkim1 = dkim1;
        return this;
    }

    /**
     * 
     * @return
     *     The dkim2
     */
    @JsonProperty("dkim2")
    public Dkim2 getDkim2() {
        return dkim2;
    }

    /**
     * 
     * @param dkim2
     *     The dkim2
     */
    @JsonProperty("dkim2")
    public void setDkim2(Dkim2 dkim2) {
        this.dkim2 = dkim2;
    }

    public ValidationResults withDkim2(Dkim2 dkim2) {
        this.dkim2 = dkim2;
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

    public ValidationResults withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
