package com.example.ejerciciospring.repositories;

import com.example.ejerciciospring.domain.entities.Instrumento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstrumentoRepository extends JpaRepository<Instrumento, Long> {
}
