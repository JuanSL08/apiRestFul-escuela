package com.tismart.controller;

import java.sql.Date;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tismart.model.Escuela;
import com.tismart.response.EscuelaResponseRest;
import com.tismart.service.IEscuelaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api-rest")
public class EscuelaRestController {

	@Autowired
	private IEscuelaService serviceEscuela;
	
	@GetMapping("/escuelas")
	public ResponseEntity<EscuelaResponseRest> consultaEsc() {
		return serviceEscuela.buscarEscuelas();
	}
	
	@GetMapping("/escuelas/{id}")
	public ResponseEntity<EscuelaResponseRest> consultaPorId(@PathVariable Integer id) {
		return serviceEscuela.buscarPorId(id);
	}
	
	@PostMapping("/escuelas")
	public ResponseEntity<EscuelaResponseRest> crear(@RequestBody Escuela request) {
		return serviceEscuela.crear(request);
	}
	
	@PutMapping("/escuelas/{id}")
	public ResponseEntity<EscuelaResponseRest> actualizar(@RequestBody Escuela request, @PathVariable Integer id) {
		return serviceEscuela.actualizar(request, id);
	}
	
	@DeleteMapping("/escuelas/{id}")
	public ResponseEntity<EscuelaResponseRest> eliminar(@PathVariable Integer id) {
		return serviceEscuela.eliminar(id);
	}
	
	
	//****************************************************
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
}
