package com.pandevs.pandevs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pandevs.pandevs.exceptions.EmailNotFoundException;
import com.pandevs.pandevs.model.User;
import com.pandevs.pandevs.service.UserService;

@RestController
@RequestMapping("/api/v1")
// CORS
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
public class UserController {
	// Mandamos a llamar Service
	private UserService userService;

	// Inyección de dependencias en el constructor
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// Mapear métodos (get, post, put, delete)
	@GetMapping("/pandelovers")
	public List<User> getMappingAll() {
		return userService.getAll();
	}
	
	// Mapear método Post que reciba un nuevo objeto y el body del mismo (@RequestBody). En Postman tengo que construir el body de la entidad en formato JSON
	@PostMapping
	public User newUser(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	// Mapear método Delete que apunte a un Id específico. Para ello, debemos permitir que el Id sea variable en el endpoint (@PathVariable)
	@DeleteMapping("/pandelovers/{id}")
	public void deleterUser(@PathVariable(name = "id") Long id) {
		userService.deleteUser(id);
	}
	
	// Mapear método get by Id que apunte a un Id específico
	@GetMapping("/pandelovers/{id}")
	public User getById(@PathVariable(name = "id") Long id) {
		return userService.getById(id);
	}
	
	// Mapear método get By Email aplicando la Query (JPQL) y la excepción
	// --- ResponseEntity<Entity> clase de Spring que me permite representar respuestas HTTP personalizables 
	// --- @RequestParam(parametro) permite recibir parámetros y valores
	// Creamos clase EmailNotFoundException y su clase controller EmailNotFoundController
	@GetMapping("/pandelovers/email")
	public ResponseEntity<User> getByEmail(@RequestParam(name = "pandemail") String email) {
		User userByEmail = userService.getByEmail(email);
		if (userByEmail == null) {
			throw new EmailNotFoundException(email);
		}
		return new ResponseEntity<User>(userByEmail, HttpStatus.OK);
	}
	
	// Mapear método update User utilizando Put. Necesitamos acceder al user mediante Id (findById) y definir el nuevo valor
	@PutMapping("/pandelovers/{id}")
	public User updateUser(@RequestBody User user, @PathVariable(name = "id") Long id) {
		return userService.updateUser(user, id);
	}
	
	
}
