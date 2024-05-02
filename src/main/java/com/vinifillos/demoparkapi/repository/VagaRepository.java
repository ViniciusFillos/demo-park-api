package com.vinifillos.demoparkapi.repository;

import com.vinifillos.demoparkapi.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
}
