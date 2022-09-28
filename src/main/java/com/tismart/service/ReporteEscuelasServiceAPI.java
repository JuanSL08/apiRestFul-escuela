package com.tismart.service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import com.tismart.model.ReporteEscuelasDTO;

import net.sf.jasperreports.engine.JRException;

public interface ReporteEscuelasServiceAPI {

	ReporteEscuelasDTO obtenerReporteEscuelas(Map<String, Object> params) throws JRException, IOException, SQLException;
	ReporteEscuelasDTO obtenerReporteEscuelasAlumnos(Map<String, Object> params) throws JRException, IOException, SQLException;
	ReporteEscuelasDTO obtenerReporteEscuelasRecursoFiscal(Map<String, Object> params) throws JRException, IOException, SQLException;
	
}
