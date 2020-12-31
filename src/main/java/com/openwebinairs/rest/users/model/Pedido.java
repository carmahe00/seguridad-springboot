package com.openwebinairs.rest.users.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Pedido {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private UserEntity cliente;
	
	@CreatedDate
	private LocalDateTime fecha;
	
	@EqualsAndHashCode.Exclude @ToString.Exclude
	@JsonManagedReference
	@OneToMany(mappedBy = "pedido",cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<LineaPedido> lineas ;
	
	public float getTotal() {
		return (float) lineas.stream()
				.mapToDouble(LineaPedido::getSubtotal)
				.sum();
	}
	
	public void addLineaPedido(LineaPedido lp) {
		lineas.add(lp);
		lp.setPedido(this);
	}
	
	public void removeLineaPedido(LineaPedido lp) {
		lineas.remove(lp);
		lp.setPedido(null);
	}
}
