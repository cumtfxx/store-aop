package com.maker.store.mapper;

import com.maker.store.model.Store;
import com.maker.store.util.MyMapper;
import org.apache.ibatis.annotations.Param;

public interface StoreMapper extends MyMapper<Store> {

    /**
     *根据商铺ID获取商铺信息
     * @param storeId
     * @return
     */
    Store selectStoreByStoreId(@Param("storeId") String storeId);

}