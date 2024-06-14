package com.example.ejerciciospring.presentation.rest;

import com.example.ejerciciospring.business.services.CategoriaServiceImpl;
import com.example.ejerciciospring.domain.entities.Categoria;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/categoria")
public class CategoriaController extends BaseControllerImp<Categoria, CategoriaServiceImpl>{
}
