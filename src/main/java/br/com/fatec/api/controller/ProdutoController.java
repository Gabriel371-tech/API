package br.com.fatec.api.controller;

import br.com.fatec.api.dto.ProdutoRequestDTO;
import br.com.fatec.api.dto.ProdutoResponseDTO;
import br.com.fatec.api.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos") // Prefixo da rota
@Tag(name = "Produtos", description = "Gerenciamento de estoque e catálogo de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @GetMapping
    @Operation(summary = "Listar produtos paginados", description = "Retorna uma página de produtos, permitindo busca opcional por nome.")
    public ResponseEntity<Page<ProdutoResponseDTO>> listarPaginado(
            @RequestParam(required = false) String nome,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(service.listarTodosPaginado(nome, page, size));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Retorna os detalhes de um produto específico com base no seu identificador único.")
    @ApiResponse(responseCode = "404", description = "Produto não encontrado")
    public ResponseEntity<ProdutoResponseDTO> buscar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Criar novo produto", description = "Cadastra um novo produto no sistema com os dados fornecidos.")
    @ApiResponse(responseCode = "201", description = "Produto criado com sucesso")
    public ResponseEntity<ProdutoResponseDTO> criar(@Valid @RequestBody ProdutoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(dto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar produto", description = "Atualiza os dados de um produto existente através do seu ID.")
    public ResponseEntity<ProdutoResponseDTO> atualizar(@PathVariable Long id,
            @Valid @RequestBody ProdutoRequestDTO dto) {
        ProdutoResponseDTO atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar produto", description = "Remove permanentemente um produto do sistema.")
    @ApiResponse(responseCode = "204", description = "Produto removido com sucesso")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
