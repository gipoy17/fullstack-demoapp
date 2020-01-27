package com.demo.miniapp.authentication;

import com.demo.miniapp.repository.entity.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUser extends User {

    private static final long serialVersionUID = 8298182470686667584L;

    public CustomUser(UserEntity userEntity, Collection<? extends GrantedAuthority> authorities) {
        super(userEntity.getUsername(), userEntity.getPassword(), authorities);
    }
}
