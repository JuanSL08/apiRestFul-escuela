package com.tismart.service;

import org.springframework.http.ResponseEntity;

import com.tismart.model.Escuela;
import com.tismart.response.EscuelaResponseRest;

public interface IEscuelaService {

	public ResponseEntity<EscuelaResponseRest> buscarEscuelas();
	public ResponseEntity<EscuelaResponseRest> buscarPorId(Integer id);
	public ResponseEntity<EscuelaResponseRest> crear(Escuela escuela);
	public ResponseEntity<EscuelaResponseRest> actualizar(Escuela escuela, Integer id);
	public ResponseEntity<EscuelaResponseRest> eliminar(Integer id);
	
}
