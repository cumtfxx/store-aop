package com.maker.store.service;

import com.maker.store.mapper.UserMapper;
import com.maker.store.model.User;
import com.maker.store.security.jwtUser.JwtUserFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userMapper.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("用户名不存在");
        }else {
            return JwtUserFactory.create(user);
        }
//        List<GrantedAuthority> authorities=new ArrayList<>();
//        List<Role> roles=user.getRoles();
//        for(Role role:roles){
//            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleType()));
//        }
//        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }
}
