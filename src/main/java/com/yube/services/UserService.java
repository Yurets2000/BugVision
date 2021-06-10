package com.yube.services;

import com.yube.model.dto.User;
import com.yube.model.entity.UserEntity;
import com.yube.model.mapping.UserMapper;
import com.yube.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User getById(String id) {
        return userMapper.map(userRepository.findById(UUID.fromString(id)).get());
    }

    public User update(User user) {
        UserEntity sourceUserEntity = userMapper.unmap(user);
        UserEntity targetUserEntity = userRepository.findById(sourceUserEntity.getId()).get();
        BeanUtils.copyProperties(sourceUserEntity, targetUserEntity, "username", "email", "password");
        UserEntity updatedUserEntity = userRepository.save(targetUserEntity);
        return userMapper.map(updatedUserEntity);
    }
}
