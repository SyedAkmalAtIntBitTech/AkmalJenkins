/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.externalcontent;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import org.json.JSONException;

/**
 *
 * @author AR
 */
public class ExternalContentProcessor {

    private final Integer companyId;
    private final ExternalContentFactory externalContentFactory;
    
    public ExternalContentProcessor(Integer companyID) throws SQLException {
        //This is where the query needs to go that instantiates the appropriate external content factory.
        this.companyId = companyID;
        externalContentFactory = new MindbodyExternalContentFactory(companyID);
    }

    public Boolean isActivated() {
        return externalContentFactory.isActivated();
    }

    public String getActivationLink() {
        return externalContentFactory.getActivationLink();
    }

    public ExternalSourceProcessedData  getListData(String query) throws JSONException, ParseException {
        return externalContentFactory.getListData(query);
    }
    
    public Map<String,String> getDetailData(String query, Object selected_object)  throws JSONException, ParseException {
        return externalContentFactory.getDetailData(query, selected_object);
    }

    public String getExternalSourceMapKeyName(String query) {
        return externalContentFactory.getExternalSourceName()+query+companyId;
    }
    public void searchEmailAndUpdateEmailOptIn(List<String> unsubscribeEmailList) {
        externalContentFactory.searchEmailAndUpdateEmailOptIn(unsubscribeEmailList);
    }
}
