package br.com.fatec.api.dto;

import br.com.fatec.api.model.Produto;
import io.swagger.v3.oas.annotations.media.Schema;

public record ProdutoResponseDTO(
        @Schema(description = "Identificador único do produto", example = "1")
        Long id,

        @Schema(description = "Nome do produto cadastrado", example = "Teclado Mecânico RGB")
        String nome,

        @Schema(description = "Preço unitário do produto", example = "250.00")
        Double preco
) {
    // Método utilitário para converter de Entity para DTO
    public static ProdutoResponseDTO fromEntity(Produto produto) {
        return new ProdutoResponseDTO(produto.getId(), produto.getNome(), produto.getPreco());
    }
}