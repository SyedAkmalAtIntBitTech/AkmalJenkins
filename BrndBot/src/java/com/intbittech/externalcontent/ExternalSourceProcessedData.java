/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.externalcontent;

import org.json.JSONException;

/**
 *
 * @author AR
 */
public abstract class ExternalSourceProcessedData {

    public abstract Object getJSON() throws JSONException ;

    public abstract Object getData_hash_map();
    
}
