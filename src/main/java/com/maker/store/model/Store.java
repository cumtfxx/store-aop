package com.maker.store.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.ResultSet;
import java.sql.SQLException;

@Entity
public class Store implements RowMapper<Store> {
    public interface add{}
    public interface update{}

    @Null(groups = {add.class},message = "添加时ID必须为空")
    @NotNull(groups = {update.class},message = "更新时ID为必填项")
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
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

    @Override
    public Store mapRow(ResultSet rs, int rowNum) throws SQLException {
        Store store=new Store();
        store.setStoreId(rs.getInt("store_id"));
        store.setStoreName(rs.getString("store_name"));
        store.setStoreIntroduce(rs.getString("store_introduce"));
        store.setBrowseTimes(rs.getInt("browse_times"));
        return store;
    }
}