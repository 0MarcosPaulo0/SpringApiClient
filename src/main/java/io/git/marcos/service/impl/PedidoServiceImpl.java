package io.git.marcos.service.impl;

import io.git.marcos.domain.repository.Pedidos;
import io.git.marcos.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repository;

    public PedidoServiceImpl(Pedidos repository) {
        this.repository = repository;
    }
}
