package com.dza.AzureActiveDirectory.controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
   @Autowired
   //@PreAuthorize("hasRole('Users')")
   @RequestMapping("/")
   public String helloWorld() {
      return "Hello World!";
   }
   
  // @PreAuthorize("hasRole('ADMIN')")
   @RequestMapping("/leo")
   public String groupOne() {
	   List<GrantedAuthority> authorities2  = new ArrayList<>();
		  authorities2.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
      return "Hello Leo Users!";
   }
   @PreAuthorize("hasRole('USER')")
   @RequestMapping("/u")
   public String groupTwo() {
      return "Hello Group 2 Users!";
   }
   @Autowired
   private OAuth2AuthorizedClientService authorizedClientService;
   
   @RequestMapping("/ll")
   public String leo(/*Model model,*/ OAuth2AuthenticationToken authentication) {
	  // final OAuth2AuthorizedClient authorizedClient = this.authorizedClientService.loadAuthorizedClient( authentication.getAuthorizedClientRegistrationId(), authentication.getName());
	   System.out.println("@@@"+authentication.getAuthorizedClientRegistrationId());
	   System.out.println("{$getName$"+ authentication.getName());
	   authentication.getPrincipal().getAuthorities().stream().peek(s -> System.out.println("------------------------$$ "+s)).count();
	     authentication.getAuthorities().stream().peek(s -> System.out.println("------------------------$$ "+s)).count();
	   System.out.println("@@@@@@@@@@@@$"+authentication.getCredentials().toString() );
	   System.out.println("{$$$$"+authentication.getDetails().toString() );
	   System.out.println("$$$$$$$"+authentication.getPrincipal().getAuthorities());
	   
	   
	   Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	   List<GrantedAuthority> authorities2  = new ArrayList<>();
	  authorities2.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	   for (GrantedAuthority grantedAuthority : authorities2){
	        System.out.println("&^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^6^7 "+ grantedAuthority.getAuthority());
	   }
	   
	   
    /*   model.addAttribute("userName", authentication.getName());
       model.addAttribute("clientName", authorizedClient.getClientRegistration().getClientName());*/
      return "Hello Leo Users! useing ll";
   }
}