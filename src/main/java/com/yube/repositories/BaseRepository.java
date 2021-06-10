package com.yube.repositories;

import com.yube.model.entity.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends PagingAndSortingRepository<T, UUID> {
    Optional<T> findById(UUID uuid);
    List<T> findAll();
}
