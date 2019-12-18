package com.dza.AzureActiveDirectory.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
 
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
 
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  
  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
      HttpServletResponse response, Authentication authentication)
      throws IOException, ServletException {
        //set our response to OK status
        response.setStatus(HttpServletResponse.SC_OK);
        
        boolean admin = false;
        
        logger.info("--------------------AT onAuthenticationSuccess(...) function!");
       /* HttpSession session = request.getSession();
        session.setAttribute("authorities", "LEO");*/
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            System.out.println("&&&&&&&&&&&&&auth.getAuthority(): "+auth.getAuthority());
            }

        List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("FOO_READ"));
            authorities.add(new SimpleGrantedAuthority("FOO_WRITE"));
           // return new  AuthenticationProvider ( authentication.getPrincipal(), authentication.getCredentials(), authorities);
            
            //throw new BadCredentialsException("Authentication failed.");
            
        response.sendRedirect("/");
        //response.setStatus(HttpServletResponse.SC_OK);// sendRedirect("/");
       /* for (GrantedAuthority auth : authentication.getAuthorities()) {
            if ("ROLE_ADMIN".equals(auth.getAuthority())){
              admin = true;
            }
        }
        
        if(admin){
          response.sendRedirect("/admin");
        }else{
          response.sendRedirect("/user");
        }*/
  }
}