package com.livraria.dto;

import lombok.Data;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Data
public class LivroDTO {
    private Long id;

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    @NotBlank(message = "O ISBN é obrigatório")
    private String isbn;

    @NotNull(message = "O preço é obrigatório")
    @Positive(message = "O preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "A quantidade é obrigatória")
    @PositiveOrZero(message = "A quantidade deve ser maior ou igual a zero")
    private Integer quantidade;

    @NotNull(message = "O ID do autor é obrigatório")
    private Long autorId;

    @NotNull(message = "O ID da categoria é obrigatório")
    private Long categoriaId;
}
