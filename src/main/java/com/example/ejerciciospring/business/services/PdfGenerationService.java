package com.example.ejerciciospring.business.services;

import com.example.ejerciciospring.domain.entities.Instrumento;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfGenerationService {

    public byte[] generateInstrumentoPdf(Instrumento instrumento) throws Exception {

        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            // Crear el documento PDF
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDocument = new PdfDocument(writer);
            Document document = new Document(pdfDocument);

            // Contenido del PDF
            Paragraph title = new Paragraph("Detalle del Instrumento").setFontSize(20);
            document.add(title);

            // Imagen del instrumento
            Image img = new Image(ImageDataFactory.create(instrumento.getImagen()));
            document.add(img.setWidth(UnitValue.createPercentValue(50)));

            // Detalles del instrumento
            document.add(new Paragraph("Cantidad vendida: " + instrumento.getCantidadVendida()));
            document.add(new Paragraph("Instrumento: " + instrumento.getInstrumento()));
            document.add(new Paragraph("Precio: $" + instrumento.getPrecio()));
            document.add(new Paragraph("Marca: " + instrumento.getMarca()));
            document.add(new Paragraph("Modelo: " + instrumento.getModelo()));
            document.add(new Paragraph("Descripción: " + instrumento.getDescripcion()));

            // Cerrar el documento
            document.close();
            return out.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}