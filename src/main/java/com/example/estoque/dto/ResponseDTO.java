package com.example.estoque.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ResponseDTO(@NotBlank(message = "O nome do produto não pode estar vazio") String name,
                          @Min(value = 1, message = "A quantidade em estoque não pode ser negativa") int quantidadeEstoque) {
}
