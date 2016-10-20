/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.modelmappers.Tooltips;

/**
 *
 * @author sandeep
 */
public interface UserPreferencesService {

    public String getUserProfileColor(Integer userId);

    public void setTooltips(Tooltips tooltips, Integer userId);

    public Tooltips getTooltips(Integer userId);

}
