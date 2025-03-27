package com.example.Kochbuch.dtos;

import com.example.Kochbuch.entities.RecipeIngredient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RespGetRecipeByIdDTO(
        @NotNull
        String id,
        @NotBlank
        String title,
        String description,
        List<RecipeIngredient> ingredients
){
}
