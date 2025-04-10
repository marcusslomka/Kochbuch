package com.example.Kochbuch.db.daos;

import com.example.Kochbuch.dtos.RecipeIngredientDTO;
import com.example.Kochbuch.entities.Recipe;
import com.example.Kochbuch.db.mapper.RecipeMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RecipeDAO {
    JdbcTemplate jdbcTemplate;

    public RecipeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String generateID(Recipe recipe) {
        String id = recipe.getId();
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        return id;
    }

    public String save(Recipe recipe) {
        jdbcTemplate.update(
                """
                        INSERT INTO recipes
                        VALUES (?,?,?)
                        """, recipe.getId(), recipe.getTitle(), recipe.getDescription());
        return recipe.getId();
    }

    public Recipe findById(String id) {
        try {
            Recipe recipe = jdbcTemplate.queryForObject(
                    " SELECT * FROM recipes WHERE ID= ?", new RecipeMapper(), id);
            return recipe;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void update(Recipe recipe) {
        jdbcTemplate.update(
                """
                        UPDATE recipes
                        SET title = ?, description = ?
                        WHERE ID = ?
                        """, recipe.getTitle(), recipe.getDescription(), recipe.getId());
    }

    public void deleteById(String id) {
        jdbcTemplate.update(
                " DELETE recipes WHERE id = ?", id);
    }
}