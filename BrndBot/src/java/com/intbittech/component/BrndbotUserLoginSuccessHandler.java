/*
 * Copyright 2015 Intbit Technologies. This software and documentation contains
 * confidential and proprietary information that is owned by Intbit
 * Technologies. Unauthorized use and distribution are strictly prohibited.
 */
package com.intbittech.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 *
 * @author Haider Khan @ Intbit
 */
@Component
public class BrndbotUserLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            return;
        }

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    
    /**
     * This method extracts the roles of currently logged-in user and returns
     * appropriate URL according to his/her role.
     * @param authentication
     * @return 
     */
    protected String determineTargetUrl(Authentication authentication) {
        String url = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority authority : authorities) {
            roles.add(authority.getAuthority());
        }
        if (isAdmin(roles)) {
            url = "/admin/organization";
        } else if (isManager(roles)) {
            url = "/user/loading";
        } else if (isCreator(roles)) {
            url = "/user/loading";
        } else if (isTempManager(roles)) {
            url = "/#/signup/company";
        } else {
            url = "/login?accessdenied=true";
        }
 
        return url;
    }
 
    private boolean isManager(List<String> roles) {
        if (roles.contains("ROLE_MANAGER")) {
            return true;
        }
        return false;
    }
 
    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }
    
    private boolean isTempManager(List<String> roles) {
        if (roles.contains("ROLE_TEMP_MANAGER")) {
            return true;
        }
        return false;
    }

    private boolean isCreator(List<String> roles) {
        if (roles.contains("ROLE_CREATOR")) {
            return true;
        }
        return false;
    }
    
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
 
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

}
