package com.example.Kochbuch.db;

import com.example.Kochbuch.entities.Recipe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class InMemoryRecipeDAO {
    Map<String,Recipe> recipes = new HashMap<>();
    public Recipe save(Recipe recipe) {
        String id = recipe.getId();
        if (id == null){
            id = UUID.randomUUID().toString();
        }
        recipes.put(id,recipe);
        return recipe;
    }
    public Recipe findById (String id){
        Recipe recipe = recipes.get(id);
        return recipe;
    }
}
