package com.itau.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itau.api.domain.SeedType;
import com.itau.api.entity.Seed;

public interface SeedRepository extends JpaRepository<Seed, Long> {
	public Seed findBySeedId(SeedType seedId);
}
