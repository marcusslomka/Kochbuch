package com.example.Kochbuch.dtos;

import com.example.Kochbuch.enums.CategorieIngredients;
import com.example.Kochbuch.enums.QuantityUnit;
import jakarta.validation.constraints.NotBlank;

public record RecipeIngredientDTO(
        @NotBlank
        String name,
        String categorie,
        @NotBlank
        int amount,
        String quantityUnit
) {
}
