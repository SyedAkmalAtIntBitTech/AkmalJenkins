/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.model.Users;
import com.intbittech.services.UsersService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Service("springSecurityUsersDetailServiceImpl")
public class SpringSecurityUsersDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsersService usersService;

    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Transactional(readOnly = true)
    @Override
     public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        Users user = usersService.findByUserName(username);
        if(user==null){
            throw new UsernameNotFoundException("Username not found");
        }
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getUserPassword(), 
                 true, true, true, true, getGrantedAuthorities(user));
    }
     
     
      private List<GrantedAuthority> getGrantedAuthorities(Users user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
         authorities.add(new SimpleGrantedAuthority(user.getFkUserRoleId().getRoleName()));
        return authorities;
    }
    
    
    

}
