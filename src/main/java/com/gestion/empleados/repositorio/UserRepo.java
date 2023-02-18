package com.gestion.empleados.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gestion.empleados.modelo.User;

@Repository
public interface UserRepo extends JpaRepository<User, String>{

	User findByUserId(String userId);

}
