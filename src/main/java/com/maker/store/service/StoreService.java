package com.maker.store.service;

import com.maker.store.dao.StoreDao;
import com.maker.store.mapper.StoreMapper;
import com.maker.store.model.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class StoreService extends BaseService<StoreMapper,Store> {
    @Autowired
    private StoreMapper storeMapper;

    public List<Store> findAll(){
        return storeMapper.selectAll();
    }

    public Store getStoreByStoreId(String StoreId){
//        return storeMapper.selectStoreByStoreId(StoreId);
        StoreDao storeDao=new StoreDao();
        return storeDao.selectStoreByStoreId(StoreId);
    }

    public Integer addStore(Store store){
        return storeMapper.insert(store);
    }

    public Integer updateStore(Store store){
        return storeMapper.updateByPrimaryKey(store);
    }

    public Integer deleteStore(Store store){
        return storeMapper.delete(store);
    }

    @Transactional
    public void addTwoStore(){
        Store store=new Store();
        store.setStoreName("qqq");
        store.setStoreIntroduce("www");
        store.setBrowseTimes(123);
        storeMapper.insert(store);
        store=new Store();
        store.setStoreName("qqqqqqqqqqqqqqqqqqqq");
        store.setStoreIntroduce("www");
        store.setBrowseTimes(123);
        storeMapper.insert(store);
    }
}
