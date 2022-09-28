package com.tismart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tismart.dao.IFacultadDao;
import com.tismart.model.Facultad;
import com.tismart.response.FacultadResponseRest;

@Service
public class FacultadServiceImpl implements IFacultadService {

	private static final Logger log = LoggerFactory.getLogger(FacultadServiceImpl.class);
	
	@Autowired
	private IFacultadDao facultadDao;
	
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<FacultadResponseRest> buscarFacultades() {
		log.info("inicio de buscarFacultades()");
		FacultadResponseRest response = new FacultadResponseRest();
		try {
			List<Facultad> list = (List<Facultad>) facultadDao.findAll();
			response.getFacultadResponse().setFacultad(list);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar facultades");
			log.error("error al consultar facultades: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<FacultadResponseRest> buscarPorId(Integer id) {
		log.info("inicio de buscarPorId()");
		FacultadResponseRest response = new FacultadResponseRest();
		List<Facultad> list = new ArrayList<>();
		try {
			Optional<Facultad> facultad = facultadDao.findById(id);
			if(facultad.isPresent()) {
				list.add(facultad.get());
				response.getFacultadResponse().setFacultad(list);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				log.error("Error en consultar facultad");
				response.setMetadata("Respuesta nok", "-1", "Facultad no encontrada");
				return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			log.error("error al consultar facultad: ", e.getMessage());
			response.setMetadata("Respuesta nok", "-1", "Error al consultar facultad por id");
			return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<FacultadResponseRest>(response, HttpStatus.OK);
	}

}
