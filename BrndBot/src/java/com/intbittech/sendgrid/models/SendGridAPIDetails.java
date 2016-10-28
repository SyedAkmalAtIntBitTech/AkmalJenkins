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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "api_key",
    "api_key_id",
    "name"
})
public class SendGridAPIDetails {

    @JsonProperty("api_key")
    private String apiKey;
    @JsonProperty("api_key_id")
    private String apiKeyId;
    @JsonProperty("name")
    private String name;

    /**
     *
     * @return The apiKey
     */
    @JsonProperty("api_key")
    public String getApiKey() {
        return apiKey;
    }

    /**
     *
     * @param apiKey The api_key
     */
    @JsonProperty("api_key")
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     *
     * @return The apiKeyId
     */
    @JsonProperty("api_key_id")
    public String getApiKeyId() {
        return apiKeyId;
    }

    /**
     *
     * @param apiKeyId The api_key_id
     */
    @JsonProperty("api_key_id")
    public void setApiKeyId(String apiKeyId) {
        this.apiKeyId = apiKeyId;
    }

    /**
     *
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    public String build() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
