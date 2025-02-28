package com.example.Kochbuch.dtos;

import jakarta.validation.constraints.NotBlank;

public record RespDeleteRecipeDTO(
        String id,
        String title
) {
}
