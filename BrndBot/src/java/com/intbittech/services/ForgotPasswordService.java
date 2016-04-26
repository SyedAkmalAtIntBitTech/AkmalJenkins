/**
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services;

import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.Category;
import com.intbittech.model.ForgotPassword;

/**
 * <code>{@link CategoryService}</code> is service layer interface for
 * communicating between Controller and DAO classes
 *
 * @author Ajit
 */
public interface ForgotPasswordService {

    /**
     * This method pass id as input and get the {@link Category} from DAO layer.
     *
     * @param categoryId the categoryId
     * @return {@link Category}
     * @throws ProcessFailed the process failed
     */
    public Integer save(ForgotPassword forgotPassword) throws ProcessFailed;

    
    /**
     * This method update {@link Category} updates existing data from the
     * database.
     *
     * @param category the category
     * @throws ProcessFailed the process failed
     */
    public void update(ForgotPassword forgotPassword) throws ProcessFailed;

    /**
     * This method delete particular {@link Category} based on the organization
     * from the database.
     *
     * @param categoryId the categoryId
     * @throws ProcessFailed the process failed
     */
    public void delete(ForgotPassword forgotPassword) throws ProcessFailed;

    public void sendMail(String email_id, String imageContextPath);

    public void updatePassword(Integer userId, String hashPassword);

    public ForgotPassword getByRandomHash(String hashURL);
    
  
}
