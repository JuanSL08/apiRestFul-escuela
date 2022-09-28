package com.tismart.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tismart.response.FacultadResponseRest;
import com.tismart.service.IFacultadService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api-rest")
public class FacultadRestController {

	@Autowired
	private IFacultadService serviceFacultad;
	
	@GetMapping("/facultades")
	public ResponseEntity<FacultadResponseRest> consultaFac() {
		return serviceFacultad.buscarFacultades();
	}
	
	@GetMapping("/facultades/{id}")
	public ResponseEntity<FacultadResponseRest> consultaPorId(@PathVariable Integer id) {
		return serviceFacultad.buscarPorId(id);
	}
	
	
	//****************************************************
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
}
