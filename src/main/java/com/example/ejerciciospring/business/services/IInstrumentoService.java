package com.example.ejerciciospring.business.services;

import com.example.ejerciciospring.domain.entities.Instrumento;

import java.util.List;

public interface IInstrumentoService {

    Instrumento crear(Instrumento instrumento);

    Instrumento getById(Long id);

    List<Instrumento> getAll();

    Instrumento actualizar(Instrumento instrumento);

    Instrumento eliminar(Instrumento instrumento);
}
