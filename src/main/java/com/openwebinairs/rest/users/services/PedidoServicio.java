package com.openwebinairs.rest.users.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.openwebinairs.rest.services.base.BaseService;
import com.openwebinairs.rest.users.model.Pedido;
import com.openwebinairs.rest.users.model.UserEntity;
import com.openwebinairs.rest.users.repos.PedidoRepositorio;

@Service
public class PedidoServicio extends BaseService<Pedido, Long, PedidoRepositorio>{

	public Page<Pedido> findAllByUser(UserEntity cliente, Pageable pageable){
		return repositorio.findByCliente(cliente, pageable);
	}
	
	public Page<Pedido> findAll(Pageable pageable){
		return repositorio.findAll(pageable);
	}
}
