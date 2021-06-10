package com.yube.repositories;

import com.yube.model.entity.RoleEntity;
import com.yube.model.enums.ERole;

import java.util.Optional;

public interface RoleRepository extends BaseRepository<RoleEntity> {
    Optional<RoleEntity> findByName(ERole name);
}
