
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
    "mail_server",
    "subdomain</em>spf",
    "domain_spf",
    "dkim"
})
public class Dns {

    @JsonProperty("mail_server")
    private MailServer mailServer;
    @JsonProperty("subdomain</em>spf")
    private SubdomainEmSpf subdomainEmSpf;
    @JsonProperty("domain_spf")
    private DomainSpf domainSpf;
    @JsonProperty("dkim")
    private Dkim dkim;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The mailServer
     */
    @JsonProperty("mail_server")
    public MailServer getMailServer() {
        return mailServer;
    }

    /**
     * 
     * @param mailServer
     *     The mail_server
     */
    @JsonProperty("mail_server")
    public void setMailServer(MailServer mailServer) {
        this.mailServer = mailServer;
    }

    public Dns withMailServer(MailServer mailServer) {
        this.mailServer = mailServer;
        return this;
    }

    /**
     * 
     * @return
     *     The subdomainEmSpf
     */
    @JsonProperty("subdomain</em>spf")
    public SubdomainEmSpf getSubdomainEmSpf() {
        return subdomainEmSpf;
    }

    /**
     * 
     * @param subdomainEmSpf
     *     The subdomain</em>spf
     */
    @JsonProperty("subdomain</em>spf")
    public void setSubdomainEmSpf(SubdomainEmSpf subdomainEmSpf) {
        this.subdomainEmSpf = subdomainEmSpf;
    }

    public Dns withSubdomainEmSpf(SubdomainEmSpf subdomainEmSpf) {
        this.subdomainEmSpf = subdomainEmSpf;
        return this;
    }

    /**
     * 
     * @return
     *     The domainSpf
     */
    @JsonProperty("domain_spf")
    public DomainSpf getDomainSpf() {
        return domainSpf;
    }

    /**
     * 
     * @param domainSpf
     *     The domain_spf
     */
    @JsonProperty("domain_spf")
    public void setDomainSpf(DomainSpf domainSpf) {
        this.domainSpf = domainSpf;
    }

    public Dns withDomainSpf(DomainSpf domainSpf) {
        this.domainSpf = domainSpf;
        return this;
    }

    /**
     * 
     * @return
     *     The dkim
     */
    @JsonProperty("dkim")
    public Dkim getDkim() {
        return dkim;
    }

    /**
     * 
     * @param dkim
     *     The dkim
     */
    @JsonProperty("dkim")
    public void setDkim(Dkim dkim) {
        this.dkim = dkim;
    }

    public Dns withDkim(Dkim dkim) {
        this.dkim = dkim;
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

    public Dns withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
