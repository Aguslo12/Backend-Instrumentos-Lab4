package com.example.ejerciciospring.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate fecha;

    private String titulo;

    private Double totalPedido;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedido_id")
    private List<PedidoDetalle> pedidoDetalles;

}
