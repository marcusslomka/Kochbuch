package com.example.Kochbuch.entities;

import com.example.Kochbuch.enums.QuantityUnit;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
public class RecipeIngredients {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    @JsonIgnore
    //um infinity Loop zu umgehen!!
    private Recipe recipe;

    @ManyToOne
    @JoinColumn(name = "ingredient_id")
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
