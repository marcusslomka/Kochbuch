package com.example.Kochbuch.dtos;

import com.example.Kochbuch.entities.RecipeIngredients;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ReqCreateNewRecipeDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        List<RecipeIngredientDTO> ingredients

) {
}

