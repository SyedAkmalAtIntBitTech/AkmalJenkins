/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.dao.impl;

import com.intbittech.dao.UserRoleDao;
import com.intbittech.exception.ProcessFailed;
import com.intbittech.model.UserRole;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleLookup;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Syed Muzamil at IntBit Technologies.
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean isRoleExist(UserRole userRole) throws ProcessFailed {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String save(UserRole userRole) throws ProcessFailed {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Integer id) throws ProcessFailed {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserRole getUserRoleById(Integer Id) throws ProcessFailed {
        Criteria criteria = sessionFactory.getCurrentSession()
                .createCriteria(UserRole.class)
                .add(Restrictions.eq("id", Id));
        if (criteria.list().isEmpty()) {
            return null;
        }
        return (UserRole) criteria.list().get(0);
    }

}
