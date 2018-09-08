package com.maker.store.service;

import com.google.common.collect.ImmutableSet;
import com.maker.store.mapper.StoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.Set;

public class BaseService <Mapp extends Mapper<M>,M> {

    private static final Set<String> EMPTY_STRING = ImmutableSet.of("null","undefined");

    @Autowired
    public StoreMapper storeMapper;
}
