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

import com.tismart.dao.IEscuelaDao;
import com.tismart.model.Escuela;
import com.tismart.response.EscuelaResponseRest;

@Service
public class EscuelaServiceImpl implements IEscuelaService {

	private static final Logger log = LoggerFactory.getLogger(EscuelaServiceImpl.class);
	
	@Autowired
	private IEscuelaDao escuelaDao;
	
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<EscuelaResponseRest> buscarEscuelas() {
		log.info("inicio de metodo buscarEscuelas()");
		EscuelaResponseRest response = new EscuelaResponseRest();
		try {
			List<Escuela> list = (List<Escuela>) escuelaDao.findAll();
			response.getEscuelaResponse().setEscuela(list);
			response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar escuelas");
			log.error("error al consultar escuelas: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<EscuelaResponseRest> buscarPorId(Integer id) {
		log.info("inicio de metodo buscarPorId");
		EscuelaResponseRest response = new EscuelaResponseRest();
		List<Escuela> list = new ArrayList<>();
		try {
			Optional<Escuela> escuela = escuelaDao.findById(id);
			if(escuela.isPresent()) {
				list.add(escuela.get());
				response.getEscuelaResponse().setEscuela(list);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				log.error("error al consultar escuela por id");
				response.setMetadata("Respuesta nok", "-1", "Error al consultar escuela");
				return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al consultar escuela por id");
			log.error("error al consultar escuela: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EscuelaResponseRest> crear(Escuela escuela) {
		log.info("inicio de metodo crear");
		EscuelaResponseRest response = new EscuelaResponseRest();
		List<Escuela> list = new ArrayList<>();
		try {
			Escuela escuelaGuardada = escuelaDao.save(escuela);
			if(escuelaGuardada != null) {
				list.add(escuelaGuardada);
				response.getEscuelaResponse().setEscuela(list);
				response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
			} else {
				log.error("error al grabar escuela");
				response.setMetadata("Respuesta nok", "-1", "escuela no guardada");
				return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al grabar escuela");
			log.error("error al grabar escuela: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EscuelaResponseRest> actualizar(Escuela escuela, Integer id) {
		log.info("inicio de metodo crear");
		EscuelaResponseRest response = new EscuelaResponseRest();
		List<Escuela> list = new ArrayList<>();
		try {
			Optional<Escuela> escuelaBuscada = escuelaDao.findById(id);
			if(escuelaBuscada.isPresent()) {
				escuelaBuscada.get().setNombre(escuela.getNombre());
				escuelaBuscada.get().setCantAlumnos(escuela.getCantAlumnos());
				escuelaBuscada.get().setRecursoFiscal(escuela.getRecursoFiscal());
				escuelaBuscada.get().setLicenciada(escuela.getLicenciada());
				escuelaBuscada.get().setClasificacion(escuela.getClasificacion());
				escuelaBuscada.get().setFechaRegistro(escuela.getFechaRegistro());
				escuelaBuscada.get().setFacultad(escuela.getFacultad());
				
				Escuela escuelaActualizar = escuelaDao.save(escuelaBuscada.get());
				
				if(escuelaActualizar != null) {
					list.add(escuelaActualizar);
					response.getEscuelaResponse().setEscuela(list);
					response.setMetadata("Respuesta ok", "00", "Respuesta exitosa");
				} else {
					log.error("error al actualizar escuela");
					response.setMetadata("Respuesta nok", "-1", "escuela no actualizada");
					return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.BAD_REQUEST);
				}
			} else {
				log.error("error al actualizar escuela");
				response.setMetadata("Respuesta nok", "-1", "escuela no actualizada");
				return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al actualizar escuela");
			log.error("error al actualizar escuela: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<EscuelaResponseRest> eliminar(Integer id) {
		log.info("inicio de metodo eliminar escuela");
		EscuelaResponseRest response = new EscuelaResponseRest();
		try {
			escuelaDao.deleteById(id);
			response.setMetadata("Respuesta ok", "00", "Respuesta eliminada");
		} catch (Exception e) {
			response.setMetadata("Respuesta nok", "-1", "Error al eliminar escuela");
			log.error("error al eliminar escuela: ", e.getMessage());
			e.getStackTrace();
			return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<EscuelaResponseRest>(response, HttpStatus.OK);
	}

}
