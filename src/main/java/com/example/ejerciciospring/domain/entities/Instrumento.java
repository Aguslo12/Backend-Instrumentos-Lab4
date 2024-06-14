package com.example.ejerciciospring.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instrumento extends Base{

    private Boolean activo;

    private String instrumento;

    private String marca;

    private String modelo;

    private String imagen;

    private String precio;

    private String costoEnvio;

    private String cantidadVendida;

    @Column(name = "descripcion",length = 1024)
    private String descripcion;

    @ManyToOne
    private Categoria categoria;
}
