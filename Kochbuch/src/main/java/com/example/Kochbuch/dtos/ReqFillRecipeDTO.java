package com.example.Kochbuch.dtos;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ReqFillRecipeDTO(

        String description,
        @NotBlank
        List<RecipeIngredientDTO> ingredients

) {
}
