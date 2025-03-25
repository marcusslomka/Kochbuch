package com.example.Kochbuch.db.mapper;

import com.example.Kochbuch.entities.Ingredient;
import com.example.Kochbuch.enums.CategorieIngredients;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IngredientMapper implements RowMapper<Ingredient> {
    public Ingredient mapRow (ResultSet rs, int rowNum) throws SQLException{
        Ingredient ingredient = new Ingredient();
        ingredient.setID(rs.getString("id"));
        ingredient.setName(rs.getString("name"));
        ingredient.setCategory(rs.getString("categorie"));
        return ingredient;
    }
}
