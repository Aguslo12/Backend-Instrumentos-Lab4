package com.example.ejerciciospring.business.services;

import com.example.ejerciciospring.domain.entities.Usuario;

import java.util.List;

public interface UsuarioService{

    Usuario crear(Usuario usuario);

    Usuario getById(Long id);

    List<Usuario> getAll();

    Usuario actualizar(Usuario usuario);

    Usuario eliminar(Usuario usuario);

    Usuario obtenerUsuario(String nombreUsuario);
}
