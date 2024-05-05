package com.realworld.feature.file.repository;

import com.realworld.feature.file.entity.ProductFileJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductFileRepository extends JpaRepository<ProductFileJpaEntity, UUID> {
}
