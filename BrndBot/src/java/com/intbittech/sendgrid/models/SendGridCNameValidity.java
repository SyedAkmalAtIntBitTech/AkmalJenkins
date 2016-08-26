
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
    "id",
    "valid",
    "validation_results"
})
public class SendGridCNameValidity extends BaseSendGridModel{

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("valid")
    private Boolean valid;
    @JsonProperty("validation_results")
    private ValidationResults validationResults;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The id
     */
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    public SendGridCNameValidity withId(Integer id) {
        this.id = id;
        return this;
    }

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

    public SendGridCNameValidity withValid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    /**
     * 
     * @return
     *     The validationResults
     */
    @JsonProperty("validation_results")
    public ValidationResults getValidationResults() {
        return validationResults;
    }

    /**
     * 
     * @param validationResults
     *     The validation_results
     */
    @JsonProperty("validation_results")
    public void setValidationResults(ValidationResults validationResults) {
        this.validationResults = validationResults;
    }

    public SendGridCNameValidity withValidationResults(ValidationResults validationResults) {
        this.validationResults = validationResults;
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

    public SendGridCNameValidity withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

}
