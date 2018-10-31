package com.maker.store.mapper;

import com.maker.store.model.Role;
import com.maker.store.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username}")
    @Results({
            @Result(id = true,column = "user_id",property = "userId",javaType = Integer.class),
            @Result(column = "username",property = "username",javaType = String.class),
            @Result(column = "password",property = "password",javaType = String.class),
            @Result(column = "nickname",property = "nickname",javaType = String.class),
            @Result(column = "user_id",property = "roles",many =@Many(select = "findRoleByUser"))
    })
    User findByUsername(String username);

    @Select("select role_type from user u,role r,user_role ur where u.user_id=#{userId} and u.user_id=ur.user_id and r.role_id=ur.role_id")
    @Results({
            @Result(id = true,column = "role_id",property = "roleId"),
            @Result(column = "role_type",property = "roleType")
    })
    List<Role> findRoleByUser(Integer userId);
}
