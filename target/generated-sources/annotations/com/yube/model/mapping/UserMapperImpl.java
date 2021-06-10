package com.yube.model.mapping;

import com.yube.model.dto.Role;
import com.yube.model.dto.User;
import com.yube.model.entity.RoleEntity;
import com.yube.model.entity.UserEntity;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2021-05-20T00:36:01+0300",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 1.8.0_261 (Oracle Corporation)"
)
@Component
public class UserMapperImpl extends UserMapper {

    @Autowired
    private StringToIdMapper stringToIdMapper;
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User map(UserEntity source) {
        if ( source == null ) {
            return null;
        }

        User user = new User();

        user.setId( stringToIdMapper.idToString( source.getId() ) );
        user.setUsername( source.getUsername() );
        user.setEmail( source.getEmail() );
        user.setPassword( source.getPassword() );
        user.setName( source.getName() );
        user.setSurname( source.getSurname() );
        user.setAge( source.getAge() );
        user.setRoles( roleEntitySetToRoleSet( source.getRoles() ) );

        return user;
    }

    @Override
    public UserEntity unmap(User source) {
        if ( source == null ) {
            return null;
        }

        UserEntity userEntity = new UserEntity();

        userEntity.setId( stringToIdMapper.stringToId( source.getId() ) );
        userEntity.setUsername( source.getUsername() );
        userEntity.setEmail( source.getEmail() );
        userEntity.setPassword( source.getPassword() );
        userEntity.setName( source.getName() );
        userEntity.setSurname( source.getSurname() );
        userEntity.setAge( source.getAge() );
        userEntity.setRoles( roleSetToRoleEntitySet( source.getRoles() ) );

        return userEntity;
    }

    protected Set<Role> roleEntitySetToRoleSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new HashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleMapper.map( roleEntity ) );
        }

        return set1;
    }

    protected Set<RoleEntity> roleSetToRoleEntitySet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleEntity> set1 = new HashSet<RoleEntity>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleMapper.unmap( role ) );
        }

        return set1;
    }
}
