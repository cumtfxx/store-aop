package com.maker.store.service;

import com.maker.store.dao.StoreDao;
import com.maker.store.mapper.StoreMapper;
import com.maker.store.model.Store;
import com.maker.store.repository.StoreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class StoreService extends BaseService<StoreMapper,Store> {
    @Autowired
    private StoreMapper storeMapper;

    @Autowired
    StoreDao storeDao;

    @Autowired
    StoreRepository storeRepository;

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    public List<Store> findAll(){
//        return storeMapper.selectAll();
        return storeRepository.findAll();
    }

    public Store getStoreByStoreId(Integer storeId){
//        return storeMapper.selectStoreByStoreId(storeId);
        return storeDao.selectStoreByStoreId(storeId);
//        return storeRepository.findById(storeId).get();
    }

    public Integer addStore(Store store){
//        return storeMapper.insert(store);
        return storeDao.addStore(store);
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

////    @Scheduled(fixedDelay = 2000)
//    @Scheduled(cron = "1-5 * * * * ? ")
////    http://cron.qqe2.com/
//    public void currentTime(){
//        logger.info("现在的时间:"+new Date());
//    }

    @Async
    public Future<Boolean> task1(){
        logger.info("task1开始的时间："+new Date());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("task1开始的时间："+new Date());
        return new AsyncResult<>(true);
    }

    @Async
    public Future<Boolean> task2(){
        logger.info("task2开始的时间："+new Date());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("task2开始的时间："+new Date());
        return new AsyncResult<>(true);
    }
}
