package com.example.ejerciciospring.repositories;

import com.example.ejerciciospring.domain.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByFechaBetween(LocalDate fechaDesde, LocalDate fechaHasta);

    @Query("SELECT YEAR(p.fecha), MONTH(p.fecha), COUNT(p) " +
            "FROM Pedido p GROUP BY YEAR(p.fecha), MONTH(p.fecha)")
    List<Object[]> countPedidosGroupedByMonthYear();

    @Query("SELECT i.instrumento, COUNT(p) FROM Pedido p " +
            "JOIN p.pedidoDetalles d JOIN d.instrumento i GROUP BY i.instrumento")
    List<Object[]> countPedidosGroupedByInstrument();

}
