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
    "api_key",
    "api_key_id",
    "name",
    "scopes"
})
public class SubUserAPIKey extends BaseSendGridModel {

    @JsonProperty("api_key")
    private String apiKey;
    @JsonProperty("api_key_id")
    private String apiKeyId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("scopes")
    private List<String> scopes = new ArrayList<String>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The apiKey
     */
    @JsonProperty("api_key")
    public String getApiKey() {
        return apiKey;
    }

    /**
     * 
     * @param apiKey
     *     The api_key
     */
    @JsonProperty("api_key")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * 
     * @return
     *     The apiKeyId
     */
    @JsonProperty("api_key_id")
    public String getApiKeyId() {
        return apiKeyId;
    }

    /**
     * 
     * @param apiKeyId
     *     The api_key_id
     */
    @JsonProperty("api_key_id")
    public void setApiKeyId(String apiKeyId) {
        this.apiKeyId = apiKeyId;
    }

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The scopes
     */
    @JsonProperty("scopes")
    public List<String> getScopes() {
        return scopes;
    }

    /**
     * 
     * @param scopes
     *     The scopes
     */
    @JsonProperty("scopes")
    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
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
