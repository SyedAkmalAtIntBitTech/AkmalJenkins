/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.externalcontent;

import com.controller.SqlMethods;
import java.text.ParseException;
import org.json.JSONException;
import org.json.simple.JSONObject;

/**
 *
 * @author AR
 */
public abstract class ExternalContentFactory {

    public SqlMethods sqlMethods;

    public ExternalContentFactory(Integer companyID) {
        sqlMethods = new SqlMethods();
    }

    public abstract Boolean isActivated();

    public abstract String getActivationLink();

    public abstract ExternalSourceProcessedData getListData(String query) throws JSONException, ParseException;

    public abstract JSONObject getDetailData(String query, Object selected_object) throws JSONException ;

    public abstract String getExternalSourceName();
}
