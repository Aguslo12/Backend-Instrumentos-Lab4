package com.example.ejerciciospring.presentation.rest;

import com.example.ejerciciospring.business.services.IInstrumentoService;
import com.example.ejerciciospring.domain.entities.Instrumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/instrumentos")
@CrossOrigin(origins = "http://localhost:5173")
public class InstrumentoController {

    @Autowired
    private IInstrumentoService instrumentoService;

    @GetMapping("/{id}")
    public ResponseEntity<Instrumento> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(instrumentoService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Instrumento>> getAll(){
        return ResponseEntity.ok().body(instrumentoService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Instrumento> crear(@RequestBody Instrumento instrumento){
        return ResponseEntity.ok().body(instrumentoService.crear(instrumento));
    }

    @PutMapping("/update")
    public ResponseEntity<Instrumento> actualizar(@RequestBody Instrumento instrumento) {
        System.out.println(instrumento.getInstrumento());
        try {
            var updatedInstrumento = instrumentoService.actualizar(instrumento);
            return ResponseEntity.ok().body(updatedInstrumento);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @PutMapping("/delete")
    public ResponseEntity<Instrumento> eliminar(@RequestBody Instrumento instrumento) {
        System.out.println(instrumento.getInstrumento());
        try {
            var updatedInstrumento = instrumentoService.eliminar(instrumento);
            return ResponseEntity.ok().body(updatedInstrumento);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }
}
