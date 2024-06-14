package com.example.ejerciciospring.business.services;

import com.example.ejerciciospring.domain.entities.Pedido;
import com.example.ejerciciospring.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    PedidoRepository pedidoRepository;

    @Override
    public Pedido crear(Pedido pedido) {return  pedidoRepository.save(pedido);}

    @Override
    public Pedido getById(Long id){
        var pedido = pedidoRepository.findById(id);
        if(pedido.isEmpty()) throw new RuntimeException("No hay instrumentos");
        return pedido.get();
    }

    @Override
    public List<Pedido> getAll(){
        List<Pedido> pedidos = pedidoRepository.findAll();
        if(pedidos.isEmpty()) throw new RuntimeException("No se encontraron instrumentos");
        return pedidos;
    }

    @Override
    public Pedido actualizar(Pedido entity) {
        var optionalEntity = pedidoRepository.findById(entity.getId());
        if (optionalEntity.isEmpty()){
            throw new RuntimeException("No se encontro una entidad con el id " + entity.getId());
        }
        var newEntity = pedidoRepository.save(entity);
        return newEntity;
    }

    @Override
    public List<Pedido> getPedidosByDateRange(LocalDate fechaDesde, LocalDate fechaHasta) {
        return pedidoRepository.findByFechaBetween(fechaDesde, fechaHasta);
    }

}
