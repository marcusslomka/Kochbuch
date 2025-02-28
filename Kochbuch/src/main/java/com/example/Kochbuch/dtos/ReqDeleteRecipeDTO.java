package com.example.Kochbuch.dtos;

import jakarta.validation.constraints.NotBlank;

public record ReqDeleteRecipeDTO(
        @NotBlank
        String id
) {
}
