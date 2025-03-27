package com.example.Kochbuch.db.daos;


import com.example.Kochbuch.db.mapper.Recipe_IngredientMapper;
import com.example.Kochbuch.entities.Recipe;
import com.example.Kochbuch.entities.RecipeIngredient;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class Recipe_IngredientDAO {
    JdbcTemplate jdbcTemplate;

    public Recipe_IngredientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String save(RecipeIngredient recipeIngredient) {
        String id = recipeIngredient.getId();
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        jdbcTemplate.update(
                """
                        INSERT INTO recipe_ingredients
                        VALUES (?,?,?,?,?)
                        """, id, recipeIngredient.getRecipe(), recipeIngredient.getIngredient(), recipeIngredient.getAmount(), recipeIngredient.getQuantityUnit());
        return id;
    }

    public RecipeIngredient findById(String id) {
        try {
            RecipeIngredient recipeIngredient = jdbcTemplate.queryForObject(
                    "SELECT * FROM recipe_ingredients WHERE ID= ?", new Recipe_IngredientMapper(),id);
            return recipeIngredient;
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
        public void deleteById (String id){
            jdbcTemplate.update(
                    " DELETE recipes WHERE id = ?", id);
        }
    }
}

