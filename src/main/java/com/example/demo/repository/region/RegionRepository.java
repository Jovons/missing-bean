package com.example.demo.repository.region;

import com.example.demo.entity.region.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<RegionEntity, Long> {
}
