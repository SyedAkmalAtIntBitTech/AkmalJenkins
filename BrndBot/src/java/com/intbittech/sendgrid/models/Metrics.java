
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
    "blocks",
    "bounce_drops",
    "bounces",
    "clicks",
    "deferred",
    "delivered",
    "invalid_emails",
    "opens",
    "processed",
    "requests",
    "spam_report_drops",
    "spam_reports",
    "unique_clicks",
    "unique_opens",
    "unsubscribe_drops",
    "unsubscribes"
})
public class Metrics {

    @JsonProperty("blocks")
    private Integer blocks;
    @JsonProperty("bounce_drops")
    private Integer bounceDrops;
    @JsonProperty("bounces")
    private Integer bounces;
    @JsonProperty("clicks")
    private Integer clicks;
    @JsonProperty("deferred")
    private Integer deferred;
    @JsonProperty("delivered")
    private Integer delivered;
    @JsonProperty("invalid_emails")
    private Integer invalidEmails;
    @JsonProperty("opens")
    private Integer opens;
    @JsonProperty("processed")
    private Integer processed;
    @JsonProperty("requests")
    private Integer requests;
    @JsonProperty("spam_report_drops")
    private Integer spamReportDrops;
    @JsonProperty("spam_reports")
    private Integer spamReports;
    @JsonProperty("unique_clicks")
    private Integer uniqueClicks;
    @JsonProperty("unique_opens")
    private Integer uniqueOpens;
    @JsonProperty("unsubscribe_drops")
    private Integer unsubscribeDrops;
    @JsonProperty("unsubscribes")
    private Integer unsubscribes;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The blocks
     */
    @JsonProperty("blocks")
    public Integer getBlocks() {
        return blocks;
    }

    /**
     * 
     * @param blocks
     *     The blocks
     */
    @JsonProperty("blocks")
    public void setBlocks(Integer blocks) {
        this.blocks = blocks;
    }

    public Metrics withBlocks(Integer blocks) {
        this.blocks = blocks;
        return this;
    }

    /**
     * 
     * @return
     *     The bounceDrops
     */
    @JsonProperty("bounce_drops")
    public Integer getBounceDrops() {
        return bounceDrops;
    }

    /**
     * 
     * @param bounceDrops
     *     The bounce_drops
     */
    @JsonProperty("bounce_drops")
    public void setBounceDrops(Integer bounceDrops) {
        this.bounceDrops = bounceDrops;
    }

    public Metrics withBounceDrops(Integer bounceDrops) {
        this.bounceDrops = bounceDrops;
        return this;
    }

    /**
     * 
     * @return
     *     The bounces
     */
    @JsonProperty("bounces")
    public Integer getBounces() {
        return bounces;
    }

    /**
     * 
     * @param bounces
     *     The bounces
     */
    @JsonProperty("bounces")
    public void setBounces(Integer bounces) {
        this.bounces = bounces;
    }

    public Metrics withBounces(Integer bounces) {
        this.bounces = bounces;
        return this;
    }

    /**
     * 
     * @return
     *     The clicks
     */
    @JsonProperty("clicks")
    public Integer getClicks() {
        return clicks;
    }

    /**
     * 
     * @param clicks
     *     The clicks
     */
    @JsonProperty("clicks")
    public void setClicks(Integer clicks) {
        this.clicks = clicks;
    }

    public Metrics withClicks(Integer clicks) {
        this.clicks = clicks;
        return this;
    }

    /**
     * 
     * @return
     *     The deferred
     */
    @JsonProperty("deferred")
    public Integer getDeferred() {
        return deferred;
    }

    /**
     * 
     * @param deferred
     *     The deferred
     */
    @JsonProperty("deferred")
    public void setDeferred(Integer deferred) {
        this.deferred = deferred;
    }

    public Metrics withDeferred(Integer deferred) {
        this.deferred = deferred;
        return this;
    }

    /**
     * 
     * @return
     *     The delivered
     */
    @JsonProperty("delivered")
    public Integer getDelivered() {
        return delivered;
    }

    /**
     * 
     * @param delivered
     *     The delivered
     */
    @JsonProperty("delivered")
    public void setDelivered(Integer delivered) {
        this.delivered = delivered;
    }

    public Metrics withDelivered(Integer delivered) {
        this.delivered = delivered;
        return this;
    }

    /**
     * 
     * @return
     *     The invalidEmails
     */
    @JsonProperty("invalid_emails")
    public Integer getInvalidEmails() {
        return invalidEmails;
    }

    /**
     * 
     * @param invalidEmails
     *     The invalid_emails
     */
    @JsonProperty("invalid_emails")
    public void setInvalidEmails(Integer invalidEmails) {
        this.invalidEmails = invalidEmails;
    }

    public Metrics withInvalidEmails(Integer invalidEmails) {
        this.invalidEmails = invalidEmails;
        return this;
    }

    /**
     * 
     * @return
     *     The opens
     */
    @JsonProperty("opens")
    public Integer getOpens() {
        return opens;
    }

    /**
     * 
     * @param opens
     *     The opens
     */
    @JsonProperty("opens")
    public void setOpens(Integer opens) {
        this.opens = opens;
    }

    public Metrics withOpens(Integer opens) {
        this.opens = opens;
        return this;
    }

    /**
     * 
     * @return
     *     The processed
     */
    @JsonProperty("processed")
    public Integer getProcessed() {
        return processed;
    }

    /**
     * 
     * @param processed
     *     The processed
     */
    @JsonProperty("processed")
    public void setProcessed(Integer processed) {
        this.processed = processed;
    }

    public Metrics withProcessed(Integer processed) {
        this.processed = processed;
        return this;
    }

    /**
     * 
     * @return
     *     The requests
     */
    @JsonProperty("requests")
    public Integer getRequests() {
        return requests;
    }

    /**
     * 
     * @param requests
     *     The requests
     */
    @JsonProperty("requests")
    public void setRequests(Integer requests) {
        this.requests = requests;
    }

    public Metrics withRequests(Integer requests) {
        this.requests = requests;
        return this;
    }

    /**
     * 
     * @return
     *     The spamReportDrops
     */
    @JsonProperty("spam_report_drops")
    public Integer getSpamReportDrops() {
        return spamReportDrops;
    }

    /**
     * 
     * @param spamReportDrops
     *     The spam_report_drops
     */
    @JsonProperty("spam_report_drops")
    public void setSpamReportDrops(Integer spamReportDrops) {
        this.spamReportDrops = spamReportDrops;
    }

    public Metrics withSpamReportDrops(Integer spamReportDrops) {
        this.spamReportDrops = spamReportDrops;
        return this;
    }

    /**
     * 
     * @return
     *     The spamReports
     */
    @JsonProperty("spam_reports")
    public Integer getSpamReports() {
        return spamReports;
    }

    /**
     * 
     * @param spamReports
     *     The spam_reports
     */
    @JsonProperty("spam_reports")
    public void setSpamReports(Integer spamReports) {
        this.spamReports = spamReports;
    }

    public Metrics withSpamReports(Integer spamReports) {
        this.spamReports = spamReports;
        return this;
    }

    /**
     * 
     * @return
     *     The uniqueClicks
     */
    @JsonProperty("unique_clicks")
    public Integer getUniqueClicks() {
        return uniqueClicks;
    }

    /**
     * 
     * @param uniqueClicks
     *     The unique_clicks
     */
    @JsonProperty("unique_clicks")
    public void setUniqueClicks(Integer uniqueClicks) {
        this.uniqueClicks = uniqueClicks;
    }

    public Metrics withUniqueClicks(Integer uniqueClicks) {
        this.uniqueClicks = uniqueClicks;
        return this;
    }

    /**
     * 
     * @return
     *     The uniqueOpens
     */
    @JsonProperty("unique_opens")
    public Integer getUniqueOpens() {
        return uniqueOpens;
    }

    /**
     * 
     * @param uniqueOpens
     *     The unique_opens
     */
    @JsonProperty("unique_opens")
    public void setUniqueOpens(Integer uniqueOpens) {
        this.uniqueOpens = uniqueOpens;
    }

    public Metrics withUniqueOpens(Integer uniqueOpens) {
        this.uniqueOpens = uniqueOpens;
        return this;
    }

    /**
     * 
     * @return
     *     The unsubscribeDrops
     */
    @JsonProperty("unsubscribe_drops")
    public Integer getUnsubscribeDrops() {
        return unsubscribeDrops;
    }

    /**
     * 
     * @param unsubscribeDrops
     *     The unsubscribe_drops
     */
    @JsonProperty("unsubscribe_drops")
    public void setUnsubscribeDrops(Integer unsubscribeDrops) {
        this.unsubscribeDrops = unsubscribeDrops;
    }

    public Metrics withUnsubscribeDrops(Integer unsubscribeDrops) {
        this.unsubscribeDrops = unsubscribeDrops;
        return this;
    }

    /**
     * 
     * @return
     *     The unsubscribes
     */
    @JsonProperty("unsubscribes")
    public Integer getUnsubscribes() {
        return unsubscribes;
    }

    /**
     * 
     * @param unsubscribes
     *     The unsubscribes
     */
    @JsonProperty("unsubscribes")
    public void setUnsubscribes(Integer unsubscribes) {
        this.unsubscribes = unsubscribes;
    }

    public Metrics withUnsubscribes(Integer unsubscribes) {
        this.unsubscribes = unsubscribes;
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

    public Metrics withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
