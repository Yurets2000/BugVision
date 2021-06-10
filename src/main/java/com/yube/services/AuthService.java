package com.yube.services;

import com.yube.model.dto.Role;
import com.yube.model.dto.User;
import com.yube.model.entity.RoleEntity;
import com.yube.model.entity.UserEntity;
import com.yube.model.enums.ERole;
import com.yube.model.mapping.RoleMapper;
import com.yube.model.mapping.UserMapper;
import com.yube.model.mapping.utils.MappingUtils;
import com.yube.model.dto.payload.request.LoginRequest;
import com.yube.model.dto.payload.request.SignupRequest;
import com.yube.model.dto.payload.response.JwtResponse;
import com.yube.model.dto.payload.response.MessageResponse;
import com.yube.repositories.RoleRepository;
import com.yube.repositories.UserRepository;
import com.yube.security.jwt.JwtUtils;
import com.yube.security.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public ResponseEntity<JwtResponse> authenticateUser(@Valid LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        JwtResponse jwtResponse = new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);

        return ResponseEntity.ok(jwtResponse);
    }

    public ResponseEntity<MessageResponse> registerUser(@Valid SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        user.setName(signUpRequest.getName());
        user.setSurname(signUpRequest.getSurname());
        user.setAge(signUpRequest.getAge());

        Set<String> strRoles = signUpRequest.getRoles();
        Set<RoleEntity> roleEntities = new HashSet<>();

        if (strRoles == null) {
            RoleEntity userRoleEntity = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roleEntities.add(userRoleEntity);
        } else {
            strRoles.forEach(role -> {
                if ("admin".equals(role)) {
                    RoleEntity adminRoleEntity = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roleEntities.add(adminRoleEntity);
                } else {
                    RoleEntity userRoleEntity = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roleEntities.add(userRoleEntity);
                }
            });
        }

        Set<Role> roles = MappingUtils.mapAllSet(roleEntities, roleMapper::map);
        user.setRoles(roles);

        UserEntity userEntity = userMapper.unmap(user);
        userRepository.save(userEntity);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
