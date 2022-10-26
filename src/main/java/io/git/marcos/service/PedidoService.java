package io.git.marcos.service;

import io.git.marcos.domain.entity.Pedido;
import io.git.marcos.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar (PedidoDTO dto);
}
