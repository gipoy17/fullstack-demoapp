package com.demo.miniapp.service;

import com.demo.miniapp.dto.UserDTO;
import com.demo.miniapp.dto.UserWrapperDTO;
import com.demo.miniapp.repository.entity.UserEntity;
import com.demo.miniapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserServiceImpl implements UserService {

    protected static final String DBUSER = "DBUSER";
    private final UserRepository userRepository;
    private final PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserWrapperDTO retrieveAllUsers() {
        List<UserDTO> users = new ArrayList<>();
        List<UserEntity> userEntities = userRepository.findAll();
        userEntities.stream().forEach((UserEntity u) -> users.add(convert(u)));
        return UserWrapperDTO.builder().users(users).build();
    }

    @Override
    public boolean deleteUser(Long id) {
        UserEntity e = userRepository.findById(id).orElse(null);
        if(Objects.nonNull(e)) {
            userRepository.delete(e);
            return true;
        }
        return false;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        UserEntity newUserEntity = buildEntity(userDTO);
        //TODO: hardcoded createdBy
        newUserEntity.setCreatedBy(DBUSER);
        newUserEntity.setCreatedDateTime(LocalDateTime.now());
        newUserEntity = userRepository.save(newUserEntity);
        return convert(newUserEntity);
    }

    private UserEntity buildEntity(UserDTO userDTO) {
        return UserEntity.builder()
                    .firstName(userDTO.getFirstName())
                    .lastName(userDTO.getLastName())
                    .username(userDTO.getUsername())
                    .password(bCryptPasswordEncoder.encode(userDTO.getPassword()))
                    .role(userDTO.getRole())
                    .build();
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        UserDTO dto = userRepository.findById(id)
                .map(record -> {
                    record.setFirstName(userDTO.getFirstName());
                    record.setLastName(userDTO.getLastName());
                    record.setUsername(userDTO.getUsername());
                    record.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
                    record.setRole(userDTO.getRole());
                    UserEntity updated = userRepository.save(record);
                    return convert(updated);
                }).orElse(null);
        //TODO: if null, throw Exception?
        return dto;
    }

    @Override
    public UserDTO retrieveUser(Long id) {
        return convert(userRepository.findById(id).orElse(null));
    }
}
