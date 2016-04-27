/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.externalcontent;

import java.sql.SQLException;
import java.text.ParseException;
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

}
