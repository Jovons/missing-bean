package com.example.demo.repository.bird;

import com.example.demo.entity.bird.BirdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface BirdRepository extends JpaRepository<BirdEntity, Long> {

    @Override
    Optional<BirdEntity> findById(@Param("id") Long aLong);
}
