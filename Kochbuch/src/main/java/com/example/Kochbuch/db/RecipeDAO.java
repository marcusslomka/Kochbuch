package com.example.Kochbuch.db;

import com.example.Kochbuch.entities.Recipe;
import com.example.Kochbuch.db.mapper.RecipeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RecipeDAO {
    JdbcTemplate jdbcTemplate;

    public RecipeDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public String save(Recipe recipe) {
        String id = recipe.getId();
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
        jdbcTemplate.update(
                """
                        INSERT INTO recipes
                        VALUES (?,?,?)
                        """, id, recipe.getTitle(), recipe.getDescription());
        return id;
    }
    public Recipe findById (String id){
        Recipe recipe = jdbcTemplate.queryForObject(
                " SELECT * FROM RECIPES WHERE ID= ?",new RecipeMapper(),id);
                return recipe;
    }
//    public String getLastInsertedId (){
//        String id = jdbcTemplate.queryForObject(
//                """
//                        SELECT LAST_INSERT_ID()
//                        """,String.class);
//        return id;
//    }
}