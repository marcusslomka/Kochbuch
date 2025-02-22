package com.example.Kochbuch.dtos;

import jakarta.validation.constraints.NotBlank;

public record RespFillRecipeDTO(
        @NotBlank
        String title
) {
}
