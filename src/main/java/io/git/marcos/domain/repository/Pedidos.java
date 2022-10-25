package io.git.marcos.domain.repository;

import io.git.marcos.domain.entity.Cliente;
import io.git.marcos.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByCliente(Cliente cliente);
}
