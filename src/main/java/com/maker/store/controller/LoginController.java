package com.maker.store.controller;

import com.maker.store.mapper.UserMapper;
import com.maker.store.security.JwtAuthenticationRequest;
import com.maker.store.security.JwtAuthenticationResponse;
import com.maker.store.service.MyUserDetailsService;
import com.maker.store.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Objects;

@RestController
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtils;

    @RequestMapping(value = "auth/login",method = RequestMethod.POST)
    public String login(@Valid JwtAuthenticationRequest request, HttpServletResponse response) throws Exception{
        String username=request.getUsername();
        String password=request.getPassword();
        String correctPassword=userDetailsService.loadUserByUsername(username).getPassword();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(password,correctPassword)){
            //通过用户名和密码创建一个 Authentication 认证对象，实现类为 UsernamePasswordAuthenticationToken
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword());
            UserDetails userDetails=userDetailsService.loadUserByUsername(authenticationToken.getPrincipal().toString());
            //如果认证对象不为空
            if (Objects.nonNull(authenticationToken)){
                authenticationToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
            }
            try {
                //通过 AuthenticationManager（默认实现为ProviderManager）的authenticate方法验证 Authentication 对象
                Authentication authentication = authenticationManager.authenticate(authenticationToken);
                //将 Authentication 绑定到 SecurityContext
                SecurityContextHolder.getContext().setAuthentication(authentication);
                //生成Token
//            String token = jwtTokenUtils.createToken(authentication,false);
                String token = jwtTokenUtils.generateToken(userDetails);
                //将Token写入到Http头部
                response.addHeader("Authority","Bearer "+token);
                return "Bearer "+token;
            }catch (BadCredentialsException authentication){
                throw new Exception("密码错误");
            }
        }else {
            return "用户名或密码错误";
        }
    }
}
