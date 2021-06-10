package com.yube.model.mapping;

import com.yube.model.dto.Role;
import com.yube.model.entity.RoleEntity;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-20T00:36:01+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl extends RoleMapper {

    @Autowired
    private StringToIdMapper stringToIdMapper;

    @Override
    public Role map(RoleEntity source) {
        if ( source == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( stringToIdMapper.idToString( source.getId() ) );
        role.setName( source.getName() );

        return role;
    }

    @Override
    public RoleEntity unmap(Role source) {
        if ( source == null ) {
            return null;
        }

        RoleEntity roleEntity = new RoleEntity();

        roleEntity.setId( stringToIdMapper.stringToId( source.getId() ) );
        roleEntity.setName( source.getName() );

        return roleEntity;
    }
}
