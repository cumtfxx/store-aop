package com.maker.store.security;

import com.maker.store.security.filter.JWTLoginFilter;
import com.maker.store.security.filter.JwtAuthorizationTokenFilter;
import com.maker.store.security.handler.AppAuthenticationSuccessHandler;
import com.maker.store.security.handler.AuthSuccessHandler;
import com.maker.store.security.handler.AuthenticationEntryPointHandler;
import com.maker.store.security.handler.RestAccessDeniedHandler;
import com.maker.store.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfiger extends WebSecurityConfigurerAdapter {

    @Autowired MyUserDetailsService userDetailsService;

    @Autowired AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

    @Autowired JwtAuthorizationTokenFilter authenticationTokenFilter;

    @Autowired AuthenticationEntryPointHandler entryPointUnauthorizedHandler;

    @Autowired RestAccessDeniedHandler restAccessDeniedHandler;

    @Autowired AuthSuccessHandler authSuccessHandler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        //security
////        http.authorizeRequests()
////                .antMatchers("/login","/css/**","/js/**","/img/**","form.html").permitAll()
////                .antMatchers("/maker/**").hasRole("MAKER")
////                .antMatchers("/","/home").hasAnyRole("USER","MAKER")
////                .anyRequest().authenticated()
////                .and()
////                .formLogin().loginPage("/login").permitAll()
////                .successHandler(appAuthenticationSuccessHandler);
////                .usernameParameter("username").passwordParameter("password")
////                .and()
////                .logout().permitAll()
////                .and()
////                .exceptionHandling().accessDeniedPage("/accessDenied");
//
//        //jwt
////        http
////                .exceptionHandling().authenticationEntryPoint(entryPointUnauthorizedHandler).accessDeniedHandler(restAccessDeniedHandler);
//
//
//        //配置请求访问策略
//        http
//                //关闭CSRF、CORS
//                .cors().disable()
//                .csrf().disable()
//                //由于使用Token，所以不需要Session
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                //验证Http请求
//                .authorizeRequests()
//                .antMatchers("/login","/css/**","/js/**","/img/**","/auth/**").permitAll()
//                .antMatchers( HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js" ).permitAll()
//                //允许所有用户访问首页 与 登录
//                .antMatchers("/", "/auth/login").permitAll()
//                //其它任何请求都要经过认证通过
//                .anyRequest().authenticated()
//                //用户页面需要用户权限
//                .antMatchers("/maker/**").hasRole("MAKER")
//                .antMatchers("/home/**").hasAnyRole("USER","MAKER")
//                .and()
//                //设置登出
//                .logout().permitAll();
////                .and()
////                .formLogin().loginPage("/login").successHandler(authSuccessHandler)
////                .permitAll();
//
//        //添加JWT filter 在
//        http
//                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
////                .addFilter(new JWTLoginFilter(authenticationManager()));
//
//        // disable page caching
////        http
////                .headers()
////                .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
////                .cacheControl();
//
//        //other
////        http
////                .formLogin().loginPage("/login")
////                .defaultSuccessUrl("/home")
////                .permitAll()
////                .and();            // 配置退出登陆信息
////        http
////                .logout()
////                .logoutSuccessUrl("/login")
////                .invalidateHttpSession(true)
////                .deleteCookies()
////                .and();
        http
                .authorizeRequests()
                .anyRequest().permitAll();       // 允许所有请求通过

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(HttpMethod.POST);
        web.ignoring().antMatchers(HttpMethod.GET);
        web.ignoring().antMatchers(HttpMethod.PUT);
        web.ignoring().antMatchers(HttpMethod.DELETE);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
