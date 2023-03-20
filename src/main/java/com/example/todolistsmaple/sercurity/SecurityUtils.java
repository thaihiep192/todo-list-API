package com.example.todolistsmaple.sercurity;

import com.example.todolistsmaple.sercurity.custom.UserPrincipal;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static UserPrincipal requester(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()){
            throw  new RuntimeException(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        var userPrinciple = authentication.getPrincipal();
        if (userPrinciple == null){
            throw  new RuntimeException(HttpStatus.UNAUTHORIZED.getReasonPhrase());
        }
        return (UserPrincipal) userPrinciple;
    }
}
