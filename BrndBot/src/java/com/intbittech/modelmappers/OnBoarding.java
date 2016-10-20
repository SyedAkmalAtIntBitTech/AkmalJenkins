/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */

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
    "CompanyName",
    "ExternalSources",
    "ColorPallete",
    "Logo",
    "Total"
})
public class OnBoarding {

    @JsonProperty("CompanyName")
    private String companyName;
    @JsonProperty("ExternalSources")
    private String externalSources;
    @JsonProperty("ColorPallete")
    private String colorPallete;
    @JsonProperty("Logo")
    private String logo;
    @JsonProperty("Total")
    private String total;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The companyName
     */
    @JsonProperty("CompanyName")
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 
     * @param companyName
     *     The CompanyName
     */
    @JsonProperty("CompanyName")
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * 
     * @return
     *     The externalSources
     */
    @JsonProperty("ExternalSources")
    public String getExternalSources() {
        return externalSources;
    }

    /**
     * 
     * @param externalSources
     *     The ExternalSources
     */
    @JsonProperty("ExternalSources")
    public void setExternalSources(String externalSources) {
        this.externalSources = externalSources;
    }

    /**
     * 
     * @return
     *     The colorPallete
     */
    @JsonProperty("ColorPallete")
    public String getColorPallete() {
        return colorPallete;
    }

    /**
     * 
     * @param colorPallete
     *     The ColorPallete
     */
    @JsonProperty("ColorPallete")
    public void setColorPallete(String colorPallete) {
        this.colorPallete = colorPallete;
    }

    /**
     * 
     * @return
     *     The logo
     */
    @JsonProperty("Logo")
    public String getLogo() {
        return logo;
    }

    /**
     * 
     * @param logo
     *     The Logo
     */
    @JsonProperty("Logo")
    public void setLogo(String logo) {
        this.logo = logo;
    }

    /**
     * 
     * @return
     *     The total
     */
    @JsonProperty("Total")
    public String getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The Total
     */
    @JsonProperty("Total")
    public void setTotal(String total) {
        this.total = total;
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
