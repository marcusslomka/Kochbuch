package com.example.Kochbuch.entities;

import com.example.Kochbuch.enums.CategorieIngredients;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="ingredients")
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank
    @Column(unique = true)
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
