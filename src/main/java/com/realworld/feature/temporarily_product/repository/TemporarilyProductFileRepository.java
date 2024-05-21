package com.realworld.feature.temporarily_product.repository;

import com.realworld.feature.temporarily_product.entity.TemporarilyProductFileJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TemporarilyProductFileRepository extends JpaRepository<TemporarilyProductFileJpaEntity, UUID> {


}
