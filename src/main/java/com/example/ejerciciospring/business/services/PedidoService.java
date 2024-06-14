package com.example.ejerciciospring.business.services;

import com.example.ejerciciospring.domain.entities.Pedido;
import com.example.ejerciciospring.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

public interface PedidoService {


    Pedido crear(Pedido pedido);

    Pedido getById(Long id);

    List<Pedido> getAll();

    Pedido actualizar(Pedido pedido);

    List<Pedido> getPedidosByDateRange(LocalDate fechaDesde, LocalDate fechaHasta);

}
