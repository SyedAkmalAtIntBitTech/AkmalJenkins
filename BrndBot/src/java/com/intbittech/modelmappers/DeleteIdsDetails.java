/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.modelmappers;

import com.intbittech.model.UserCompanyIds;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author ilyas
 */
public class DeleteIdsDetails extends UserCompanyIds implements Serializable{
    
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }
}
