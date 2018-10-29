package com.maker.store.service;

import com.maker.store.dao.StoreDao;
import com.maker.store.mapper.StoreMapper;
import com.maker.store.model.Store;
import com.maker.store.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Service
public class StoreService extends BaseService<StoreMapper,Store> {
    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    StoreDao storeDao;

    @Autowired
    StoreRepository storeRepository;

    public List<Store> findAll(){
//        return storeMapper.selectAll();
        return storeRepository.findAll();
    }

    public Store getStoreByStoreId(Integer storeId){
//        return storeMapper.selectStoreByStoreId(storeId);
        return storeDao.selectStoreByStoreId(storeId);
//        return storeRepository.findById(storeId).get();
    }

    public void addStore(Store store){
//        return storeMapper.insert(store);
        storeDao.addStore(store);
//        storeRepository.save(store);
    }

    public void updateStore(Store store){
//        return storeMapper.updateByPrimaryKey(store);
        storeDao.update(store);
//        storeRepository.save(store);
    }

    public void deleteStore(Integer storeId){
//        return storeMapper.delete(store);
        storeDao.deleteStore(storeId);
//        storeRepository.deleteById(storeId);
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
