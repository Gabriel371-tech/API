package br.com.fatec.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProdutoRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres")
        @Schema(description = "Nome do produto", example = "Teclado Mecânico RGB")
        String nome,

        @NotNull(message = "O preço é obrigatório")
        @Positive(message = "O preço deve ser maior que zero")
        @Schema(description = "Preço unitário do produto", example = "250.00")
        Double preco
) {}
