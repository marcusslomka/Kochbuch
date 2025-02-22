package com.example.Kochbuch.entities;

import com.example.Kochbuch.enums.CategorieIngredients;

import jakarta.validation.constraints.NotBlank;


public class Ingredient {
    private long id;
    @NotBlank

    private String name;
    private CategorieIngredients category;


    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public CategorieIngredients getCategory() {
        return category;
    }

    public void setCategory(CategorieIngredients category) {
        this.category = category;
    }
}
