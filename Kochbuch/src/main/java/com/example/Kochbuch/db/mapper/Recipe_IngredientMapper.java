package com.example.Kochbuch.db.mapper;

import com.example.Kochbuch.entities.Ingredient;
import com.example.Kochbuch.entities.Recipe;
import com.example.Kochbuch.entities.RecipeIngredient;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Recipe_IngredientMapper implements RowMapper<RecipeIngredient> {
    public RecipeIngredient mapRow(ResultSet rs, int rowNum) throws SQLException {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setId(rs.getString("id"));
        recipeIngredient.setRecipe((Recipe) rs.getObject("recipe"));
        recipeIngredient.setIngredient((Ingredient) rs.getObject("ingredient"));
        recipeIngredient.setAmount(rs.getInt("amount"));
        recipeIngredient.setQuantityUnit(rs.getString("quantityUnit"));
        return recipeIngredient;
    }
}
