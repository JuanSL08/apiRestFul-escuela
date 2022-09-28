package com.tismart.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.condition.ParamsRequestCondition;

import com.tismart.model.ReporteEscuelasDTO;
import com.tismart.service.ReporteEscuelasServiceAPI;

import net.sf.jasperreports.engine.JRException;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api-rest/report")
public class ReporteEscuelasController {

	@Autowired
	private ReporteEscuelasServiceAPI reporteEscuelasServiceAPI;
	
	@GetMapping(path = "/escuelas/download")
	public ResponseEntity<Resource> downloadReporteEscuelas()
			throws JRException, IOException, SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tipo", "pdf");
		ReporteEscuelasDTO dto = reporteEscuelasServiceAPI.obtenerReporteEscuelas(params);

		InputStreamResource streamResource = new InputStreamResource(dto.getStream());
		MediaType mediaType = null;
		mediaType = MediaType.APPLICATION_PDF;
		/*
		if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		} else {
			mediaType = MediaType.APPLICATION_PDF;
		} 
		*/
		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
				.contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
	}
	
	@GetMapping(path = "/escuelas-alumnos/download")
	public ResponseEntity<Resource> downloadReporteEscuelasAlumnos()
			throws JRException, IOException, SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tipo", "pdf");
		ReporteEscuelasDTO dto = reporteEscuelasServiceAPI.obtenerReporteEscuelasAlumnos(params);

		InputStreamResource streamResource = new InputStreamResource(dto.getStream());
		MediaType mediaType = null;
		mediaType = MediaType.APPLICATION_PDF;
		/*
		if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		} else {
			mediaType = MediaType.APPLICATION_PDF;
		} 
		*/
		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
				.contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
	}
	
	@GetMapping(path = "/escuelas-recursofiscal/download")
	public ResponseEntity<Resource> downloadReporteEscuelasRecursoFiscal()
			throws JRException, IOException, SQLException {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("tipo", "pdf");
		ReporteEscuelasDTO dto = reporteEscuelasServiceAPI.obtenerReporteEscuelasRecursoFiscal(params);

		InputStreamResource streamResource = new InputStreamResource(dto.getStream());
		MediaType mediaType = null;
		mediaType = MediaType.APPLICATION_PDF;
		/*
		if (params.get("tipo").toString().equalsIgnoreCase(TipoReporteEnum.EXCEL.name())) {
			mediaType = MediaType.APPLICATION_OCTET_STREAM;
		} else {
			mediaType = MediaType.APPLICATION_PDF;
		} 
		*/
		return ResponseEntity.ok().header("Content-Disposition", "inline; filename=\"" + dto.getFileName() + "\"")
				.contentLength(dto.getLength()).contentType(mediaType).body(streamResource);
	}
	
}
