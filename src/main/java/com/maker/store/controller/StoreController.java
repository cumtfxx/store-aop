package com.maker.store.controller;

import com.maker.store.model.Store;
import com.maker.store.service.StoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RequestMapping(value = "api/stores",produces = {APPLICATION_JSON_UTF8_VALUE})
@RestController
@Api(value="api/stores",tags="StoresApi",description = "商铺接口")
public class StoreController {
    @Autowired
    private StoreService storeService;

    private Logger logger=LoggerFactory.getLogger(StoreController.class);

    @GetMapping(value = "/getStore/{storeId}")
    @ApiOperation(value = "根据ID获取商铺信息",response = Store.class,responseContainer = "list")
    public ResponseEntity getStore(@ApiParam(value ="storeId",required = true)@PathVariable String storeId){
        return ResponseEntity.ok(storeService.getStoreByStoreId(storeId));
    }
}
