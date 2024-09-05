package com.pandevs.pandevs.model;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

// Para convertir mi Java class en una Entidad, necesito las anotaciones de JPA (Java Persistence API). Además, JPA necesita un constructor vacío.
/*
 * Anotaciones de JPA:
 * 	- @Entity para indicar que dicha clase es una entidad OMR
 * 	- @Table(name = "tableName", schema = "db") para indicar el nombre que llevará mi tabla en el esquema especificado
 * 	- @Id para indicar una llave primaria (PK)
 * 	- @GeneratedValue nos permite crear una estrategia para la generación de la PK
 * 	- @Column me permite configurar cada atributo como una columna de una tabla
 * */

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_user")
	private Long id;
	@Column(name = "username", length = 60, nullable = false, unique = true)
	private String username;
	@Column(name = "email", length = 120, nullable = false, unique = true)
	private String email;
	@Column(name = "password", length = 24, nullable = false, unique = false)
	private String password;
	
	// Definir las relaciones ( 1 : N) OneToMany. Un usuario puede tener muchas órdenes
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonIgnore
	private List<Order> orders;
	
	
	// Constructor vacío para JPA
	public User() {
		
	}
	
	// Constructor
	public User(Long id, String username, String email, String password) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
	}

	// Getters y Setters
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	// toString
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password + "]";
	}

	// hashCode
	@Override
	public int hashCode() {
		return Objects.hash(email, id, password, username);
	}

	//equals
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(password, other.password) && Objects.equals(username, other.username);
	}
	
	
	
	
}
