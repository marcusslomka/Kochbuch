package com.example.Kochbuch.dtos;

import com.example.Kochbuch.entities.RecipeIngredient;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ReqUpdateRecipeDTO(

        String title,
        String description,
        @NotBlank
        List<RecipeIngredient> ingredients

) {
}
