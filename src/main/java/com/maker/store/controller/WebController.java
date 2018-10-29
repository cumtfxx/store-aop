package com.maker.store.controller;


import com.maker.store.model.Store;
import com.maker.store.service.StoreService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Controller
public class WebController {
    @Autowired
    private StoreService storeService;

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
    public String login(){
        return "login";
    }

    @GetMapping(value = "/home")
    public String home(){
        return "home";
    }

    @GetMapping(value = "/maker")
    public String maker(){
        return "maker";
    }

    @GetMapping(value = "/accessDenied")
    public String accessDenied(){
        return "accessDenied";
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
