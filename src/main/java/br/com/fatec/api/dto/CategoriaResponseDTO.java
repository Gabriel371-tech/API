package br.com.fatec.api.dto;

import br.com.fatec.api.model.Categoria;
import io.swagger.v3.oas.annotations.media.Schema;

public record CategoriaResponseDTO(
        @Schema(description = "Identificador único da categoria", example = "1")
        Long id,

        @Schema(description = "Nome da categoria cadastrada", example = "Eletrônicos")
        String nome
) {
    public static CategoriaResponseDTO fromEntity(Categoria categoria) {
        return new CategoriaResponseDTO(categoria.getId(), categoria.getNome());
    }
}
