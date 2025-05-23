package com.example.Kochbuch.db;

import jakarta.annotation.PostConstruct;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DbConfig {
    JdbcTemplate jdbcTemplate;

    public DbConfig(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void test() {
        jdbcTemplate.execute(
                """
                        CREATE TABLE IF NOT EXISTS recipes (
                        id varchar(255) PRIMARY KEY,
                        title varchar(255),
                        description varchar(255)
                        );
                        CREATE TABLE IF NOT EXISTS ingredients (
                        id varchar(255) NOT NULL PRIMARY KEY,
                        name varchar(255) NOT NULL,
                        categorie varchar(255) NOT NULL
                        );
                        CREATE TABLE IF NOT EXISTS recipe_ingredients (
                        id varchar(255) NOT NULL  PRIMARY KEY,
                        recipe_id varchar(255) NOT NULL,
                        ingredient_id varchar(255) NOT NULL,
                        amount int NOT NULL,
                        quantityUnit varchar(255) NOT NULL,
                        foreign key (recipe_id) references recipes(id),
                        foreign key (ingredient_id) references ingredients(id)
                        );
                        """
        );
    }
}
