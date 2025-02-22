package com.example.Kochbuch.entities;

import com.example.Kochbuch.enums.CategorieIngredients;
import com.example.Kochbuch.services.RecipeService;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(unique = true)
    private String name;
    private CategorieIngredients categorie;


    public @NotBlank String getName() {
        return name;
    }

    public void setName(@NotBlank String name) {
        this.name = name;
    }

    public CategorieIngredients getCategorie() {
        return categorie;
    }

    public void setCategorie( CategorieIngredients categorie) {
        this.categorie = categorie;
    }
}
