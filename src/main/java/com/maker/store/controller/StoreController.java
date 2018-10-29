package com.maker.store.controller;

import com.maker.store.model.Store;
import com.maker.store.service.StoreService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.constraints.NotNull;
import java.io.File;
import java.io.IOException;
import java.util.Date;

import static org.springframework.http.MediaType.*;

@RequestMapping(value = "api/stores",produces = {APPLICATION_JSON_UTF8_VALUE})
@RestController
@Api(value="api/stores",tags="StoresApi",description = "商铺接口")
public class StoreController {
    @Autowired
    private StoreService storeService;

    private Logger logger=LoggerFactory.getLogger(StoreController.class);

    @GetMapping(value = "/getStore/{storeId}",headers = "Accept=application/json"
//            ,consumes="application/json",produces="application/json"
    )
    @ApiOperation(value = "根据ID获取商铺信息",response = Store.class,responseContainer = "list")
    @ApiResponses(@ApiResponse(code=200,message = "获取信息成功"))
    public ResponseEntity getStore(@ApiParam(value ="storeId",required = true)@PathVariable Integer storeId){
        return ResponseEntity.ok(storeService.getStoreByStoreId(storeId));
    }

    @PostMapping(value = "/addStore",params = "action=save")
    @ApiOperation(value = "增加一个商铺",response = Store.class)
    @ApiResponses(@ApiResponse(code=200,message = "增加成功"))
    public void addStore(@Validated({Store.add.class}) @RequestBody Store store){
        storeService.addStore(store);
    }

    @PostMapping(value = "/transactional",params = "action=save")
    @ApiOperation(value = "增加两个商铺",response = Store.class,responseContainer = "list")
    @ApiResponses(@ApiResponse(code=200,message = "增加成功"))
    public void addTwoStore(){
        storeService.addTwoStore();
    }

    @PutMapping(value = "/update")
    @ApiOperation(value = "更改店铺信息")
    @ApiResponses(@ApiResponse(code=200,message = "修改成功"))
    public void updateStore(@Validated({Store.update.class}) @RequestBody Store store){
        storeService.updateStore(store);
    }

    @DeleteMapping(value = "/deleteStore/{storeId}")
    @ApiOperation(value = "根据ID删除店铺",response = Store.class)
    @ApiResponses(@ApiResponse(code=200,message = "删除成功"))
    public void deleteStore(@ApiParam(value ="storeId",required = true)@PathVariable Integer storeId){
        storeService.deleteStore(storeId);
    }

    @PostMapping(value = "/upload")
    @ApiOperation(value = "上传文件")
    @ApiResponses(@ApiResponse(code=200,message = "上传成功"))
    public ModelAndView test(MultipartFile file) throws IOException {
        ModelAndView modelAndView=new ModelAndView();
        if(!file.isEmpty()){
            modelAndView.addObject("fileName",file.getOriginalFilename());
            modelAndView.addObject("fileSize",file.getSize());
        }
        modelAndView.setViewName("test");
        file.transferTo(new File("d:\\demo.png"));
        return modelAndView;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    @GetMapping(value = "/date")
    @ApiOperation(value = "日期格式化")
    public ResponseEntity date(@RequestParam Date date){
        return ResponseEntity.ok(date);
    }
}
