package com.gestion.empleados.controlador;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.empleados.excepciones.ResourceNotFoundException;
import com.gestion.empleados.modelo.Persona;
import com.gestion.empleados.modelo.User;
import com.gestion.empleados.repositorio.PersonaRepositorio;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonaControlador {

	@Autowired
	private PersonaRepositorio repositorio;

	//este metodo sirve para listar todas las personas
	@GetMapping("/personas")
	public List<Persona> listarTodosLosEmpleados() {
		return repositorio.findAll();
	}
	//este metodo es para validar el login
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Persona userData) {
		System.out.println(userData);
		Persona persona=repositorio.findByUsername(userData.getUsername());
		if(persona.getPassword().equals(userData.getPassword()))
			return ResponseEntity.ok(persona);
		return (ResponseEntity<?>) ResponseEntity.internalServerError();
	}

	//este metodo sirve para guardar la persona
	@PostMapping("/personas")
	public Persona guardarPersona(@RequestBody Persona persona) {
		return repositorio.save(persona);
	}
	
	//este metodo sirve para buscar por Tipo de usuario
		@GetMapping("/personas/{tipousuario}")
		public ResponseEntity<Persona> obtenerPersonaPorTipoUser(@PathVariable String tipousuario){
			Persona persona = repositorio.findByTipousuario(tipousuario)
						            .orElseThrow(() -> new ResourceNotFoundException("No existe la persona con el ID : " + tipousuario));
				return ResponseEntity.ok(persona);
		}
		
	//este metodo sirve para buscar una persona
	@GetMapping("/personas/{id}")
	public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id){
		Persona persona = repositorio.findById(id)
					            .orElseThrow(() -> new ResourceNotFoundException("No existe la persona con el ID : " + id));
			return ResponseEntity.ok(persona);
	}
	
	//este metodo sirve para actualizar Persona
	@PutMapping("/personas/{id}")
	public ResponseEntity<Persona> actualizarPersona(@PathVariable Long id,@RequestBody Persona detallesPersona){
		Persona persona = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe la persona con el ID : " + id));
		
		persona.setName(detallesPersona.getName());
		persona.setUsername(detallesPersona.getUsername());
		persona.setEmail(detallesPersona.getEmail());
		persona.setPassword(detallesPersona.getPassword());
		persona.setTipousuario(detallesPersona.getTipousuario());
		
		Persona personaActualizada = repositorio.save(persona);
		return ResponseEntity.ok(personaActualizada);
    }
	
	//este metodo sirve para eliminar una persona
	@DeleteMapping("/personas/{id}")
	public ResponseEntity<Map<String,Boolean>> eliminarPersona(@PathVariable Long id){
		Persona persona = repositorio.findById(id)
				            .orElseThrow(() -> new ResourceNotFoundException("No existe la persona con el ID : " + id));
		
		repositorio.delete(persona);
		Map<String, Boolean> respuesta = new HashMap<>();
		respuesta.put("eliminar",Boolean.TRUE);
		return ResponseEntity.ok(respuesta);
    }
}














