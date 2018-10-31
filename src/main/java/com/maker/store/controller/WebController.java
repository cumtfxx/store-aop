package com.maker.store.controller;


import com.maker.store.model.JwtUser;
import com.maker.store.model.Store;
import com.maker.store.security.AuthenticationException;
import com.maker.store.security.JwtAuthenticationManager;
import com.maker.store.security.JwtAuthenticationRequest;
import com.maker.store.security.JwtAuthenticationResponse;
import com.maker.store.service.MyUserDetailsService;
import com.maker.store.service.StoreService;
import com.maker.store.util.JwtTokenUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    @Autowired
    private StoreService storeService;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    MyUserDetailsService userDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JwtAuthenticationManager authenticationManager;

//    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
//    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException {
//        final Authentication authentication = authenticationManager.authenticate
//                (new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authentication);        // Reload password post-security so we can generate token
//        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
//        final String token = jwtTokenUtil.generateToken(userDetails);        // Return the token
//        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
//    }

    @GetMapping(value = "/")
    @ApiOperation(value = "首页/全部商铺信息")
    public String index(Model model){
        model.addAttribute("stores",storeService.findAll());
        return "index";
    }

    @PostMapping(value = "/api/add")
    @ApiOperation(value = "增加商铺并跳转到首页")
    public ModelAndView add(Store store){
        ModelAndView modelAndView=new ModelAndView();
        storeService.addStore(store);
        modelAndView.addObject("stores",storeService.findAll());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView login(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    private final static String JWT_HEADER_NAME = "Authorization";

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        return user;
    }

    @GetMapping(value = "/generate")
    public @ResponseBody String generateToken(@RequestParam String username,@RequestParam String password){
        JwtAuthenticationRequest jwtAuthenticationRequest = new JwtAuthenticationRequest(username, password);
        String myUsername=jwtAuthenticationRequest.getUsername();
        UserDetails userDetails=userDetailsService.loadUserByUsername(myUsername);
        String token=jwtTokenUtil.generateToken(userDetails);
        return token;
    }
    @GetMapping("/registration")
    public String register(@RequestParam String username, HttpServletResponse response) {
        UserDetails userDetails=userDetailsService.loadUserByUsername(username);
        String jwt = jwtTokenUtil.generateToken(userDetails);
        response.setHeader(JWT_HEADER_NAME, jwt);
        return String.format("JWT for %s : %s", username, jwt);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(value = "/home")
    public String home(){
        return "home";
    }

    @PreAuthorize("hasRole('MAKER')")
    @GetMapping(value = "/maker")
    public String maker(){
        return "maker";
    }

    @GetMapping(value = "/accessDenied")
    public String accessDenied(){
        return "accessDenied";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    /**
     * 获得当前用户名称
     * */
    private String getUsername(){
        // 从SecurityContex中获得Authentication对象代表当前用户的信息
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("username = " + username);
        return username;
    }

    /**
     * 获得当前用户权限
     * */
    private String getAuthority(){
        // 获得Authentication对象，表示用户认证信息。
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = new ArrayList<String>();
        // 将角色名称添加到List集合
        for (GrantedAuthority a : authentication.getAuthorities()) {
            roles.add(a.getAuthority());
        }
        System.out.println("role = " + roles);
        return roles.toString();
    }

//    @ModelAttribute
//    public void findStoreByStoreId(Model model){
//        model.addAttribute("stores",storeService.findAll());
//    }
//
//    @GetMapping(value = "/all")
//    public String index(){
//        return "index";
//    }

}
