package com.example.ejerciciospring.presentation.rest;


import com.example.ejerciciospring.business.services.PedidoService;
import com.example.ejerciciospring.domain.entities.Pedido;
import com.example.ejerciciospring.domain.entities.PreferenceMP;
import com.example.ejerciciospring.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @GetMapping("/group-by-month-year")
    public ResponseEntity<?> getPedidosGroupedByMonthYear() {
        List<Object[]> results = pedidoRepository.countPedidosGroupedByMonthYear();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/group-by-instrument")
    public ResponseEntity<?> getPedidosGroupedByInstrument() {
        List<Object[]> results = pedidoRepository.countPedidosGroupedByInstrument();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> getPedidosByFechaBetween(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaDesde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaHasta) {
        List<Pedido> pedidos = pedidoRepository.findByFechaBetween(fechaDesde, fechaHasta);

        // Agrupamos los pedidos por año y mes
        List<Object[]> groupedData = pedidos.stream()
                .collect(Collectors.groupingBy(p -> {
                    LocalDate date = p.getFecha();
                    return YearMonth.from(date);
                }, Collectors.counting()))
                .entrySet().stream()
                .map(e -> new Object[] { e.getKey().getYear(), e.getKey().getMonthValue(), e.getValue() })
                .collect(Collectors.toList());

        return ResponseEntity.ok(groupedData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(pedidoService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pedido>> getAll() {
        try {
            var items = pedidoService.getAll();
            return ResponseEntity.ok().body(items);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Pedido> crear(@RequestBody Pedido pedido){
        return ResponseEntity.ok().body(pedidoService.crear(pedido));
    }

    @PutMapping("/update")
    public ResponseEntity<Pedido> actualizar(@RequestBody Pedido pedido) {
        try {
            var updatedPedido = pedidoService.actualizar(pedido);
            return ResponseEntity.ok().body(updatedPedido);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
    @PostMapping("/api/create_preference_mp")
    public PreferenceMP crearPreferenciaMercadoPago(@RequestBody Pedido pedido){
        MercadoPagoController cMercadoPago = new MercadoPagoController();
        PreferenceMP preference = cMercadoPago.getPreferenciaIdMercadoPago(pedido);
        crear(pedido);
        return preference;
    }

}
