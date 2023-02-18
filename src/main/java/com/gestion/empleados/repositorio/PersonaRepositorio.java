package com.gestion.empleados.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.empleados.modelo.Persona;


@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Long>{

	Persona findByUsername(String username);

	Optional<Persona> findByTipousuario(String tipousuario);
	

}
