package io.git.marcos.service.impl;

import io.git.marcos.domain.entity.Cliente;
import io.git.marcos.domain.entity.ItemPedido;
import io.git.marcos.domain.entity.Pedido;
import io.git.marcos.domain.entity.Produto;
import io.git.marcos.domain.repository.Clientes;
import io.git.marcos.domain.repository.ItemsPedido;
import io.git.marcos.domain.repository.Pedidos;
import io.git.marcos.domain.repository.Produtos;
import io.git.marcos.exception.RegraNegocioException;
import io.git.marcos.rest.dto.ItensPedidoDTO;
import io.git.marcos.rest.dto.PedidoDTO;
import io.git.marcos.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente)
                .orElseThrow(()-> new RegraNegocioException("Código de cliente inválido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);

        List<ItemPedido> itensPedido = converterItens(pedido, dto.getItens());
        repository.save(pedido);
        itensPedidoRepository.saveAll(itensPedido);
        pedido.setItens(itensPedido);

        return pedido;
    }

    private List<ItemPedido> converterItens(Pedido pedido, List<ItensPedidoDTO> itens){
        if(itens.isEmpty()){
            throw new RegraNegocioException("Não é possívvel realizar um pedido sem itens.");
        }

        return itens
                .stream()
                .map(dto ->{
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                        .findById(idProduto)
                        .orElseThrow(()-> new RegraNegocioException("Código de produto inválido " + idProduto));

            ItemPedido itemPedido = new ItemPedido();
            itemPedido.setQuantidade(dto.getQuantidade());
            itemPedido.setPedido(pedido);
            itemPedido.setProduto(produto);
            return itemPedido;
        }).collect(Collectors.toList());
    }
}
