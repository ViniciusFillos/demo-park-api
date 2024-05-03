package com.vinifillos.demoparkapi.repository;

import com.vinifillos.demoparkapi.entity.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    Optional<Vaga> findByCoddigo(String codigo);
}
