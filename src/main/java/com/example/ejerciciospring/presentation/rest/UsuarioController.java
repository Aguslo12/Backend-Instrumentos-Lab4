package com.example.ejerciciospring.presentation.rest;

import com.example.ejerciciospring.business.services.UsuarioService;
import com.example.ejerciciospring.domain.entities.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "http://localhost:5173")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(usuarioService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Usuario>> getAll(){
        return ResponseEntity.ok().body(usuarioService.getAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario){
        return ResponseEntity.ok().body(usuarioService.crear(usuario));
    }

    @PutMapping("/update")
    public ResponseEntity<Usuario> actualizar(@RequestBody Usuario usuario) {
        System.out.println(usuario.getNombreUsuario());
        try {
            var updatedInstrumento = usuarioService.actualizar(usuario);
            return ResponseEntity.ok().body(updatedInstrumento);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @PutMapping("/delete")
    public ResponseEntity<Usuario> eliminar(@RequestBody Usuario usuario) {
        System.out.println(usuario.getNombreUsuario());
        try {
            var updatedInstrumento = usuarioService.eliminar(usuario);
            return ResponseEntity.ok().body(updatedInstrumento);
        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    @GetMapping("/{nombreUsuario}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable String nombreUsuario) {
        Usuario usuario = usuarioService.obtenerUsuario(nombreUsuario);
        return ResponseEntity.ok(usuario);
    }

}
