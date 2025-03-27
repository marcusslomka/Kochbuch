package com.example.Kochbuch.entities;

import com.example.Kochbuch.enums.CategorieIngredients;

import jakarta.validation.constraints.NotBlank;


public class Ingredient {
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String category;


    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getId(){ return this.id;};
    public void setID(@NotBlank String id){this.id = id;}
}