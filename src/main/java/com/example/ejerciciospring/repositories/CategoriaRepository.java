package com.example.ejerciciospring.repositories;

import com.example.ejerciciospring.domain.entities.Categoria;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends BaseRepository<Categoria, Long>{
}
