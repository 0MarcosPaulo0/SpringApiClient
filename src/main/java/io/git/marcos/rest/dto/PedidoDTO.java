package io.git.marcos.rest.dto;

import io.git.marcos.domain.entity.ItemPedido;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PedidoDTO {
    private Integer cliente;
    private BigDecimal total;
    private List<ItensPedidoDTO> itens;
}
