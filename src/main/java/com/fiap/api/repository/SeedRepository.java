package com.fiap.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fiap.api.domain.Seed;
import com.fiap.api.types.SeedType;

public interface SeedRepository extends JpaRepository<Seed, Long> {
	public Seed findBySeedId(SeedType seedId);
}
