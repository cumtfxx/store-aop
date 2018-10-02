package com.maker.store.controller;


import com.maker.store.model.Store;
import com.maker.store.service.StoreService;
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
    public String index(Model model){
        model.addAttribute("stores",storeService.findAll());
        return "index";
    }

    @PostMapping(value = "/api/add")
    public ModelAndView add(Store store, ModelAndView modelAndView){
        storeService.addStore(store);
        modelAndView.addObject("stores",storeService.findAll());
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @ModelAttribute
    public void findStoreByStoreId(Model model){
        model.addAttribute("stores",storeService.findAll());
    }

    @GetMapping(value = "/all")
    public String index(){
        return "index";
    }

}
