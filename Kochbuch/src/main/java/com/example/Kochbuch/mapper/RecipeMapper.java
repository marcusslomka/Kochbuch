package com.example.Kochbuch.mapper;

import com.example.Kochbuch.entities.Recipe;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper Object to map recipe table entry with recipe object
public class RecipeMapper implements RowMapper<Recipe> {
    public Recipe mapRow(ResultSet rs, int rowNum) throws SQLException{
        Recipe recipe = new Recipe();
        recipe.setId(rs.getString("id"));
        recipe.setDescription(rs.getString("description"));
        recipe.setTitle(rs.getString("title"));
        return recipe;
    }
}