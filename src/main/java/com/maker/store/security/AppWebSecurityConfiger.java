package com.maker.store.security;

import com.maker.store.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    AppAuthenticationSuccessHandler appAuthenticationSuccessHandler;

    @Autowired
    JwtAuthorizationTokenFilter authenticationTokenFilter;

    @Autowired
    JwtAuthenticationEntryPoint unauthorizedHandler;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//
//                .antMatchers("/login","/css/**","/js/**","/img/**","form.html").permitAll()
//                .antMatchers("/maker/**").hasRole("MAKER")
//                .antMatchers("/","/home").hasAnyRole("USER","MAKER")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login")
//                .successHandler(appAuthenticationSuccessHandler)
//                .usernameParameter("username").passwordParameter("password")
//                .and()
//                .logout().permitAll()
//                .and()
//                .exceptionHandling().accessDeniedPage("/accessDenied");

        http
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/login","/css/**","/js/**","/img/**","form.html","/generate","swagger-ui.html").permitAll()
                .antMatchers( HttpMethod.GET, "/", "/*.html", "/favicon.ico", "/**/*.html", "/**/*.css", "/**/*.js" ).permitAll()

                .antMatchers("/auth/**").permitAll()
                .anyRequest().authenticated();


        http
                .addFilterBefore(authenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);

        // disable page caching
        http
                .headers()
                .frameOptions().sameOrigin()  // required to set for H2 else H2 Console will be blank.
                .cacheControl();
        http
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/goIndex")
                .permitAll()
                .and();            // 配置退出登陆信息
        http
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies()
                .and();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
