package com.maker.store.service;

import com.maker.store.mapper.StoreMapper;
import com.maker.store.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService extends BaseService<StoreMapper,Store> {
    @Autowired
    private StoreMapper storeMapper;

    public List<Store> findAll(){
        return storeMapper.selectAll();
    }

    public Store getStoreByStoreId(String StoreId){
        return storeMapper.selectStoreByStoreId(StoreId);
    }

    public void addStore(Store store){
        storeMapper.insert(store);
    }

    public Integer updateStore(Store store){
        return storeMapper.updateByPrimaryKey(store);
    }

    public void deleteStore(Store store){
        storeMapper.delete(store);
    }
}
