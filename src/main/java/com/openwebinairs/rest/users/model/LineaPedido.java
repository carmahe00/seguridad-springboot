package com.openwebinairs.rest.users.model;


import javax.persistence.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class LineaPedido {
	@Id
	@GeneratedValue
	private Long id;
	private Double subtotal;
	@ManyToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
}
