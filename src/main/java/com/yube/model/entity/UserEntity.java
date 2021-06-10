package com.yube.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class UserEntity extends BaseEntity {

    @NotBlank
    @Size(min = 3, max = 20)
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Size(min = 3, max = 50)
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(min = 3, max = 120)
    @Column(nullable = false)
    private String password;

    @Size(min = 1, max = 50)
    @Column
    private String name;

    @Size(min = 1, max = 50)
    @Column
    private String surname;

    @Positive
    @Column
    private Integer age;

    @ManyToMany
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleEntity> roles = new HashSet<>();
}