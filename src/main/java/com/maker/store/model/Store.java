package com.maker.store.model;

import javax.persistence.*;

public class Store {
    @Id
    @Column(name = "store_id")
    private Integer storeId;

    @Column(name = "store_name")
    private String storeName;

    @Column(name = "store_introduce")
    private String storeIntroduce;

    @Column(name = "browse_times")
    private Integer browseTimes;

    public Store(){}
    /**
     * @return store_id
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * @param storeId
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /**
     * @return store_name
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * @param storeName
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * @return store_introduce
     */
    public String getStoreIntroduce() {
        return storeIntroduce;
    }

    /**
     * @param storeIntroduce
     */
    public void setStoreIntroduce(String storeIntroduce) {
        this.storeIntroduce = storeIntroduce;
    }

    /**
     * @return browse_times
     */
    public Integer getBrowseTimes() {
        return browseTimes;
    }

    /**
     * @param browseTimes
     */
    public void setBrowseTimes(Integer browseTimes) {
        this.browseTimes = browseTimes;
    }
}