package com.example.estoque.resource;

import com.example.estoque.domain.Produto;
import com.example.estoque.dto.ResponseDTO;
import com.example.estoque.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProdutoResource {
    @Autowired
    ProdutoService produtoService;

    @PostMapping("/produto/create")
    public ResponseEntity<ResponseDTO> createProductInDataBase(@RequestBody ResponseDTO request) {
        Produto produto = produtoService.createdProductInDataBase(request.name(), request.quantidadeEstoque(), request.limiteEstoqueBaixo());
        return ResponseEntity.ok(new ResponseDTO(produto.getName(), produto.getQuantidadeEstoque(), produto.getLimiteEstoqueBaixo()));
    }

    @GetMapping("/produtos/estoque-baixo")
    public ResponseEntity<List<Produto>> getLessStockQuantity(){
        List<Produto> produto = produtoService.checkLowStockQuantity();
        return ResponseEntity.ok(produto);
    }


}
