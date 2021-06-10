package com.yube.security.services;

import com.yube.model.dto.User;
import com.yube.model.entity.UserEntity;
import com.yube.model.mapping.UserMapper;
import com.yube.repositories.UserRepository;
import com.yube.security.model.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        User user = userMapper.map(userEntity);
        return UserDetailsImpl.build(user);
    }

}