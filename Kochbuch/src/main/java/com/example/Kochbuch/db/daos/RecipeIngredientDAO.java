package com.example.Kochbuch.db.daos;


import com.example.Kochbuch.db.mapper.Recipe_IngredientMapper;
import com.example.Kochbuch.entities.RecipeIngredient;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class RecipeIngredientDAO {
    JdbcTemplate jdbcTemplate;

    public RecipeIngredientDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void save(RecipeIngredient recipeIngredient) {
        String id = recipeIngredient.getId();
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        jdbcTemplate.update(
                """
                        INSERT INTO recipe_ingredients
                        VALUES (?,?,?,?,?)
                        """, id, recipeIngredient.getRecipe().getId() ,recipeIngredient.getIngredient().getId(),recipeIngredient.getAmount(), recipeIngredient.getQuantityUnit());
    }

    public RecipeIngredient findById(String id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM recipe_ingredients WHERE ID= ?", new Recipe_IngredientMapper(),id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void update(RecipeIngredient recipeIngredient) {
        jdbcTemplate.update(
                """
                        UPDATE recipe_ingredients
                        SET recipe = ?, ingredient = ?, amount = ?, quantityUnit = ?
                        WHERE ID = ?
                        """, recipeIngredient.getRecipe(),recipeIngredient.getIngredient(),recipeIngredient.getAmount(),recipeIngredient.getQuantityUnit(),recipeIngredient.getId());
    }
        public void deleteById (String id){
            jdbcTemplate.update(
                    " DELETE recipe_ingredients WHERE id = ?", id);
        }
}

