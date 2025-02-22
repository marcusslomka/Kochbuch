package com.example.Kochbuch.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespCreateNewRecipeDTO(
        @NotNull
        long id,
        @NotBlank
        String title
){
}
