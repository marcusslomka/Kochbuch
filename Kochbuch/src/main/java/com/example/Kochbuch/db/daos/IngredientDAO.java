package com.example.Kochbuch.db.daos;

import com.example.Kochbuch.db.mapper.IngredientMapper;
import com.example.Kochbuch.entities.Ingredient;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IngredientDAO {
    JdbcTemplate jdbcTemplate;

    public IngredientDAO (JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate;}

    public String save (Ingredient ingredient){
        String  id = ingredient.getId();
        if (id == null){
            id = UUID.randomUUID().toString();
        }
        jdbcTemplate.update(
                """
                        INSERT INTO ingredients
                        VALUES (?,?,?)
                        """, id,ingredient.getName(),ingredient.getCategory());
                return id;
    }
    public Ingredient findById(String id){
        try{
            Ingredient ingredient = jdbcTemplate.queryForObject(
                    "SELECT * FROM ingredients WHERE ID = ?", new IngredientMapper(),id);
            return ingredient;
        } catch (EmptyResultDataAccessException e){
            return null;
        }
    }
    public void update(Ingredient ingredient) {
        jdbcTemplate.update(
                """
                        UPDATE ingredients
                        SET name = ?, SET category = ?
                        WHERE ID = ?
                        """, ingredient.getName(), ingredient.getCategory(), ingredient.getId());
    }
    public void deleteById (String id){
        jdbcTemplate.update(
                " DELETE ingredients WHERE id = ?", id);
    }
}