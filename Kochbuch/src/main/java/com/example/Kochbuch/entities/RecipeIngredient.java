package com.example.Kochbuch.entities;

import jakarta.validation.constraints.NotNull;


public class RecipeIngredient {

    private String id;
    private Recipe recipe;
    private Ingredient ingredient;

    @NotNull
    private int amount;

    private String quantityUnit;

    public void setId(String id){ this.id = id;}

    public String getId(){return this.id;}

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(@NotNull int amount) {
        this.amount = amount;
    }

    public  String getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit( String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }
}
