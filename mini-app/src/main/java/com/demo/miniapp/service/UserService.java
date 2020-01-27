package com.demo.miniapp.service;

import com.demo.miniapp.dto.UserDTO;
import com.demo.miniapp.dto.UserWrapperDTO;
import com.demo.miniapp.repository.entity.UserEntity;

import java.util.Objects;

public interface UserService {

    UserWrapperDTO retrieveAllUsers();
    UserDTO retrieveUser(Long id);
    boolean deleteUser(Long id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);

    default UserDTO convert(UserEntity u) {
        if(Objects.nonNull(u)) {
            return UserDTO.builder()
                    .id(u.getId())
                    .lastName(u.getLastName())
                    .firstName(u.getFirstName())
                    .username(u.getUsername())
                    .password(u.getPassword())
                    .role(u.getRole())
                    .createdBy(u.getCreatedBy())
                    .createdDateTime(u.getCreatedDateTime())
                    .updatedBy(u.getUpdatedBy())
                    .updatedDateTime(u.getUpdatedDateTime())
                    .build();
        }
        return null;
    }
}
