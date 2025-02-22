package com.example.Kochbuch.entities;

import com.example.Kochbuch.enums.QuantityUnit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class RecipeIngredients {

    private long id;


    //um infinity Loop zu umgehen!!
    private Recipe recipe;


    private Ingredient ingredient;

    @NotNull
    private int amount;

    private QuantityUnit quantityUnit;

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

    @NotNull
    public int getAmount() {
        return amount;
    }

    public void setAmount(@NotNull int amount) {
        this.amount = amount;
    }

    public  QuantityUnit getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit( QuantityUnit quantityUnit) {
        this.quantityUnit = quantityUnit;
    }
}
