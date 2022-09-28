package com.tismart.service;

import org.springframework.http.ResponseEntity;

import com.tismart.response.FacultadResponseRest;

public interface IFacultadService {

	public ResponseEntity<FacultadResponseRest> buscarFacultades();
	public ResponseEntity<FacultadResponseRest> buscarPorId(Integer id);
	
}
