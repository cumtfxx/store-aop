package com.maker.store.service;

import com.maker.store.mapper.UserMapper;
import com.maker.store.model.Role;
import com.maker.store.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }
        List<GrantedAuthority> authorities=new ArrayList<>();
        List<Role> roles=user.getRoles();
        for(Role role:roles){
            authorities.add(new SimpleGrantedAuthority(role.getRoleType()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
