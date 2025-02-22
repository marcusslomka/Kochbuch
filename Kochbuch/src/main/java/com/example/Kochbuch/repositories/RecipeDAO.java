package com.example.Kochbuch.repositories;

import com.example.Kochbuch.entities.Recipe;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RecipeDAO {
    JdbcTemplate jdbcTemplate;

    public RecipeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Recipe save(Recipe recipe) {
        String id = recipe.getId();
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        jdbcTemplate.update(
                """
                        INSERT INTO recipes
                        VALUES (?,?,?)
                        """, id, recipe.getTitle(), recipe.getDescription());
        return recipe;
    }
}