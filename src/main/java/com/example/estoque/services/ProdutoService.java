package com.example.estoque.services;

import com.example.estoque.domain.Produto;
import com.example.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    ProdutoRepository produtoRepository;

    public Produto createdProductInDataBase(String name, int quantidadeEstoque, int limiteEstoqueBaixo){
        Produto produto = new Produto();
        produto.setName(name);
        produto.setQuantidadeEstoque(quantidadeEstoque);
        produto.setLimiteEstoqueBaixo(limiteEstoqueBaixo);
        produtoRepository.save(produto);
        return produto;
    }

    public void alertLowStock() {
        List<Produto> estoque = checkLowStockQuantity();
        if(estoque.isEmpty()) {

        }
    }

    public List<Produto> checkLowStockQuantity() {
        return produtoRepository.findByQuantidadeEstoqueLessThanEqual(5);
    }
}
