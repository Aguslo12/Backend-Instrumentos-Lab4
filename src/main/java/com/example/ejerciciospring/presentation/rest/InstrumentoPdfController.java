package com.example.ejerciciospring.presentation.rest;

import com.example.ejerciciospring.business.services.IInstrumentoService;
import com.example.ejerciciospring.business.services.PdfGenerationService;
import com.example.ejerciciospring.domain.entities.Instrumento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/instrumentos")
@CrossOrigin(origins = "*")
public class InstrumentoPdfController {

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @Autowired
    private IInstrumentoService instrumentoService; // Asumiendo que tienes un servicio para los instrumentos

    @GetMapping("/{id}/pdf")
    public ResponseEntity<Resource> downloadInstrumentoPdf(@PathVariable Long id) {
        // Obtener el instrumento desde la base de datos
        Instrumento instrumento = instrumentoService.getById(id); // Asumiendo que tienes un método para obtener el instrumento por id

        if (instrumento == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            // Generar el PDF del instrumento
            byte[] pdfBytes = pdfGenerationService.generateInstrumentoPdf(instrumento);

            // Construir la respuesta para descargar el PDF
            ByteArrayResource resource = new ByteArrayResource(pdfBytes);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"instrumento.pdf\"")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(resource);
        } catch (Exception e) {
            // Manejar cualquier excepción que pueda ocurrir al generar el PDF
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}