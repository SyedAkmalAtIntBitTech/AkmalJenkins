/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.services.impl;

import com.intbittech.model.UserProfile;
import com.intbittech.model.Users;
import com.intbittech.model.UsersRoleLookup;
import com.intbittech.services.UserRoleLookUpService;
import com.intbittech.services.UsersService;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
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

    private Logger logger = Logger.getLogger(SpringSecurityUsersDetailServiceImpl.class);

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserRoleLookUpService userRoleLookUpService;
    
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
        
        if (user == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        UserProfile userProfile = new UserProfile(user, getGrantedAuthorities(user));
        return userProfile;
    }

    private List<GrantedAuthority> getGrantedAuthorities(Users user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        try{

            List<UsersRoleLookup> usersRoleLookUp = userRoleLookUpService.getAllUserRolesByUserId(user);

            for (int i = 0; i< usersRoleLookUp.size(); i++){
                UsersRoleLookup userRole = usersRoleLookUp.get(i);
                authorities.add(new SimpleGrantedAuthority(userRole.getRoleId().getRoleName()));
            }
            return authorities;
            
        }catch (Throwable throwable){
            logger.error(throwable);
        }
        return authorities;
    }

}
