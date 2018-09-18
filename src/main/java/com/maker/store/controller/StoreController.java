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
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.*;

@RequestMapping(value = "api/stores",produces = {APPLICATION_JSON_UTF8_VALUE})
@RestController
@Api(value="api/stores",tags="StoresApi",description = "商铺接口")
public class StoreController {
    @Autowired
    private StoreService storeService;

    private Logger logger=LoggerFactory.getLogger(StoreController.class);

    @GetMapping(value = "/getStore/{storeId}",headers = "Accept=application/json",consumes={ALL_VALUE},produces="application/json")
    @ApiOperation(value = "根据ID获取商铺信息",response = Store.class,responseContainer = "list")
    public ResponseEntity getStore(@ApiParam(value ="storeId",required = true)@PathVariable String storeId){
        return ResponseEntity.ok(storeService.getStoreByStoreId(storeId));
    }

    @PostMapping(value = "/addStore")
    @ApiOperation(value = "增加一个商铺",response = Store.class,responseContainer = "list")
    public void addStore(@RequestParam String name,
                         @RequestParam String introduce,
                         @RequestParam Integer times){
        Store store = new Store();
        store.setStoreName(name);
        store.setStoreIntroduce(introduce);
        store.setBrowseTimes(times);
        storeService.addStore(store);
    }

    @PutMapping(value = "/update/{id}")
    @ApiOperation(value = "更改店铺信息")
    public void updateStore(@PathVariable Integer id,
                            @RequestParam String name,
                            @RequestParam String introduce,
                            @RequestParam Integer times){
        Store store = new Store();
        store.setStoreId(id);
        store.setStoreName(name);
        store.setStoreIntroduce(introduce);
        store.setBrowseTimes(times);
        storeService.updateStore(store);
    }

    @DeleteMapping(value = "/deleteStore/{storeId}")
    @ApiOperation(value = "根据ID删除店铺",response = Store.class,responseContainer = "list")
    public void deleteStore(@ApiParam(value ="storeId",required = true)@PathVariable String storeId){
        Store store=storeService.getStoreByStoreId(storeId);
        storeService.deleteStore(store);
    }
}
