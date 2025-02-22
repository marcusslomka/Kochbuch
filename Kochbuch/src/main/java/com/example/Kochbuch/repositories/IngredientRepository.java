package com.example.Kochbuch.repositories;

import com.example.Kochbuch.entities.Ingredient;


import java.util.Optional;

public interface IngredientRepository  {
    Optional<Ingredient>findByName(String name);
}
