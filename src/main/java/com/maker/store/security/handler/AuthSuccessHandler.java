package com.maker.store.security.handler;

import com.maker.store.model.User;
import com.maker.store.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserDetailsService userDetailsService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
            // TODO Auto-generated method stub
            User user = new User();
            user.setUsername(authentication.getName());
//            CookieUtil.addCookie(response, "name", user.getUsername(), 0);
// 		    String url=request.getHeader("Referer").substring(request.getHeader("Referer").indexOf('=')+2);
// 		    LogUtil.error(getClass(), url);
            SecurityContextHolder holder=new SecurityContextHolder();
//        SecurityContextHolder.getContext().getAuthentication().getPrincipal()
            UserDetails userDetails= userDetailsService.loadUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
            response.sendRedirect("home");
            new JwtTokenUtil().generateToken(userDetails);
    }
}
