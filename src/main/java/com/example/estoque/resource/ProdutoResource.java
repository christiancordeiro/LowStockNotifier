package com.example.estoque.resource;

import com.example.estoque.domain.Produto;
import com.example.estoque.dto.ResponseDTO;
import com.example.estoque.services.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api")
public class ProdutoResource {
    @Autowired
    ProdutoService produtoService;

    @PostMapping("/produto/create")
    public ResponseEntity<ResponseDTO> createProductInDataBase(@Valid @RequestBody ResponseDTO request) {
        Produto produto = produtoService.createdProductInDataBase(request.name(), request.quantidadeEstoque());
        return ResponseEntity.ok(new ResponseDTO(produto.getName(), produto.getQuantidadeEstoque()));
    }

    @GetMapping("/produtos/estoque-baixo")
    public ResponseEntity<List<Produto>> getLessStockQuantity(){
        List<Produto> produto = produtoService.checkLowStockQuantity();
        return ResponseEntity.ok(produto);
    }


}
