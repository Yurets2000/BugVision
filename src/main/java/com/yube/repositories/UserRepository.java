package com.yube.repositories;

import com.yube.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository extends BaseRepository<UserEntity> {
    Optional<UserEntity> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
