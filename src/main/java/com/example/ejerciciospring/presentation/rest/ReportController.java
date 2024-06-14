package com.example.ejerciciospring.presentation.rest;
import com.example.ejerciciospring.business.services.PedidoService;

import com.example.ejerciciospring.domain.entities.Pedido;
import com.example.ejerciciospring.domain.entities.PedidoDetalle;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class ReportController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/report")
    public void generateExcelReport(
            @RequestParam(required = false) String fechaDesde,
            @RequestParam(required = false) String fechaHasta,
            HttpServletResponse response) throws IOException {

        LocalDate startDate = fechaDesde != null ? LocalDate.parse(fechaDesde) : null;
        LocalDate endDate = fechaHasta != null ? LocalDate.parse(fechaHasta) : null;

        List<Pedido> pedidos = pedidoService.getPedidosByDateRange(startDate, endDate);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Pedidos");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Fecha Pedido");
        headerRow.createCell(1).setCellValue("Instrumento");
        headerRow.createCell(2).setCellValue("Marca");
        headerRow.createCell(3).setCellValue("Modelo");
        headerRow.createCell(4).setCellValue("Cantidad");
        headerRow.createCell(5).setCellValue("Precio");
        headerRow.createCell(6).setCellValue("Subtotal");

        int rowNum = 1;
        for (Pedido pedido : pedidos) {
            for (PedidoDetalle detalle : pedido.getPedidoDetalles()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(pedido.getFecha().toString());
                row.createCell(1).setCellValue(detalle.getInstrumento().getInstrumento());
                row.createCell(2).setCellValue(detalle.getInstrumento().getMarca());
                row.createCell(3).setCellValue(detalle.getInstrumento().getModelo());
                row.createCell(4).setCellValue(detalle.getCantidad());
                row.createCell(5).setCellValue(detalle.getInstrumento().getPrecio());
                row.createCell(6).setCellValue(detalle.getCantidad() * Integer.parseInt(detalle.getInstrumento().getPrecio()));
            }
        }

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=pedidos_report.xlsx");

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}