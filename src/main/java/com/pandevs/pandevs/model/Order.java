package com.pandevs.pandevs.model;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "orders")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_order")
	private Long id;
	
	@Column(name = "fecha_orden", length = 16, nullable = false, unique = false)
	@Temporal(TemporalType.TIMESTAMP) // Me permiter identificar campos de tipo Date y puedo definir si trabajo con fecha y hora o algún otro valor
	private Date fechaDePedido;
	
	@Column(name = "total", length = 10, nullable = false, unique = false)
	private BigDecimal total;
	
	@Column(name = "estatus", length = 16, nullable = false, unique = false)
	@Enumerated(EnumType.STRING) // para recibir y enviar Enum a la DB en forma de String
	private OrderStatus estatus;
	
	@Column(name = "factura", nullable = false, unique = false)
	@Lob // Indica que el tipo de dato es BLOB y se envia a la DB como archivo de gran tamaño
	private byte[] factura;
	
	// Definir las relaciones entre las entidades ( N : 1 ) ManyToOne
	@ManyToOne
	@JoinColumn(name = "id_user", nullable = false)
	@JsonIgnore
	private User user;
	
	
	public Order() {
	}

	public Order(Long id, Date fechaDePedido, BigDecimal total, OrderStatus estatus, byte[] factura) {
		this.id = id;
		this.fechaDePedido = fechaDePedido;
		this.total = total;
		this.estatus = estatus;
		this.factura = factura;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaDePedido() {
		return fechaDePedido;
	}

	public void setFechaDePedido(Date fechaDePedido) {
		this.fechaDePedido = fechaDePedido;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public OrderStatus getEstatus() {
		return estatus;
	}

	public void setEstatus(OrderStatus estatus) {
		this.estatus = estatus;
	}

	public byte[] getFactura() {
		return factura;
	}

	public void setFactura(byte[] factura) {
		this.factura = factura;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", fechaDePedido=" + fechaDePedido + ", total=" + total + ", estatus=" + estatus
				+ ", factura=" + Arrays.toString(factura) + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(factura);
		result = prime * result + Objects.hash(estatus, fechaDePedido, id, total);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		return estatus == other.estatus && Arrays.equals(factura, other.factura)
				&& Objects.equals(fechaDePedido, other.fechaDePedido) && Objects.equals(id, other.id)
				&& Objects.equals(total, other.total);
	}
	
	
	
	
}
