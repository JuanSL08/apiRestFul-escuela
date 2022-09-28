package com.tismart.model;

import java.io.ByteArrayInputStream;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReporteEscuelasDTO {

	private String fileName;
	private ByteArrayInputStream stream;
	private int length;
	
}
