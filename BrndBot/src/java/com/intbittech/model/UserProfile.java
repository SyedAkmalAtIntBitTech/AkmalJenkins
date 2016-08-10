/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.model;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Haider Khan @ Intbit
 */
public class UserProfile extends User{
    private Users user;

    public Users getUser() {
        return user;
    }
    public UserProfile(Users user, Collection<? extends GrantedAuthority> authorities) {
       this(user.getUserName(), user.getUserPassword(), authorities);
       this.user =  user;
    }
    
    public UserProfile(String username, String password, Collection<? extends GrantedAuthority> authorities) {
       super(username, password, authorities);
    }

    public UserProfile(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,authorities);
    }
    
    
}
