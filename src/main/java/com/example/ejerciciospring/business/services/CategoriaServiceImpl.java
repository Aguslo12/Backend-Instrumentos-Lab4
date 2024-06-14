package com.example.ejerciciospring.business.services;

import com.example.ejerciciospring.domain.entities.Categoria;
import com.example.ejerciciospring.repositories.BaseRepository;
import com.example.ejerciciospring.repositories.CategoriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl extends BaseServiceImp<Categoria, Long> implements CategoriaService{

    @Autowired
    private CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(BaseRepository<Categoria, Long> baseRepository, CategoriaRepository categoriaRepository) {
        super(baseRepository);
        this.categoriaRepository = categoriaRepository;
    }
}