package com.maker.store.service;

import com.maker.store.mapper.StoreMapper;
import com.maker.store.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreService extends BaseService<StoreMapper,Store> {
    @Autowired
    StoreMapper storeMapper;

    public Store getStoreByStoreId(String StoreId){
        return storeMapper.selectStoreByStoreId(StoreId);
    }
}
