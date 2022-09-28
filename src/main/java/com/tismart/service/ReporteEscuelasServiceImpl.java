package com.tismart.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tismart.commons.JasperReportManager;
import com.tismart.model.ReporteEscuelasDTO;

import net.sf.jasperreports.engine.JRException;

@Service
public class ReporteEscuelasServiceImpl implements ReporteEscuelasServiceAPI {

	@Autowired
	private JasperReportManager reportManager;

	@Autowired
	private DataSource dataSource;
	
	@Override
	public ReporteEscuelasDTO obtenerReporteEscuelas(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "Escuela_Report";
		ReporteEscuelasDTO dto = new ReporteEscuelasDTO();
		String extension = ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteEscuelasDTO obtenerReporteEscuelasAlumnos(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "Escuela_Alumnos_Report";
		ReporteEscuelasDTO dto = new ReporteEscuelasDTO();
		String extension = ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}
	
	@Override
	public ReporteEscuelasDTO obtenerReporteEscuelasRecursoFiscal(Map<String, Object> params)
			throws JRException, IOException, SQLException {
		String fileName = "Escuela_RecursoFiscal_Report";
		ReporteEscuelasDTO dto = new ReporteEscuelasDTO();
		String extension = ".pdf";
		dto.setFileName(fileName + extension);

		ByteArrayOutputStream stream = reportManager.export(fileName, params.get("tipo").toString(), params,
				dataSource.getConnection());

		byte[] bs = stream.toByteArray();
		dto.setStream(new ByteArrayInputStream(bs));
		dto.setLength(bs.length);

		return dto;
	}

}
