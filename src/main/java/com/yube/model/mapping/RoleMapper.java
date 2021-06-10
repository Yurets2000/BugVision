package com.yube.model.mapping;

import com.yube.model.dto.Role;
import com.yube.model.entity.RoleEntity;
import org.mapstruct.Mapper;

@Mapper(uses = {StringToIdMapper.class}, componentModel = "spring")
public abstract class RoleMapper implements BaseMapper<RoleEntity, Role> {
    @Override
    public abstract Role map(RoleEntity source);

    @Override
    public abstract RoleEntity unmap(Role source);
}
