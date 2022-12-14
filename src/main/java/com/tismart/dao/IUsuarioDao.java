package com.tismart.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.tismart.model.Usuario;


public interface IUsuarioDao extends CrudRepository<Usuario, Long> {

	public Usuario findByNombreUsuario(String nombreUsuario);
	
	@Query("select u from Usuario u where u.nombreUsuario=?1")
	public Usuario findByIdNombreUsuarioV2(String nombreUsuario);
	
}
