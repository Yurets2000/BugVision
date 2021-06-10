package com.yube.model.entity;

import com.yube.model.enums.ERole;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity(name = "roles")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RoleEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private ERole name;
}