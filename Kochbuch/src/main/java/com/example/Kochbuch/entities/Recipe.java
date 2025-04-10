package com.example.Kochbuch.entities;


import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;
import java.util.List;


public class Recipe {

    private String id;
    private String title;
    private String description;


    private List<RecipeIngredient> ingredients = new ArrayList<>();

    public List<RecipeIngredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<RecipeIngredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public @NotBlank String getTitle() {
        return title;
    }

    public void setTitle(@NotBlank String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
