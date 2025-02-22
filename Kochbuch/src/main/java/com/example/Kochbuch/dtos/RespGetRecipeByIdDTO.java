package com.example.Kochbuch.dtos;

import com.example.Kochbuch.entities.RecipeIngredients;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RespGetRecipeByIdDTO(
        @NotNull
        long id,
        @NotBlank
        String title,
        String description,
        List<RecipeIngredients> ingredients
){
}
