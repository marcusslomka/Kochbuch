package com.example.Kochbuch.dtos;

import jakarta.validation.constraints.NotBlank;

public record RespUpdateRecipeDTO(
        @NotBlank
        String title,
        String id
) {
}
