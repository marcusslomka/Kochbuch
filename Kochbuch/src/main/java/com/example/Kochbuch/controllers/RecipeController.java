package com.example.Kochbuch.controllers;

import com.example.Kochbuch.dtos.*;
import com.example.Kochbuch.services.RecipeService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/kochbuch/recipe")
public class RecipeController {
    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;

    }

    @PostMapping
    public ResponseEntity<RespCreateNewRecipeDTO> createNewRecipe(@RequestBody @Validated ReqCreateNewRecipeDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createNewRecipe(dto));
    }

    @PutMapping("/{recipe_id}")
    public ResponseEntity<RespUpdateRecipeDTO> updateCreatedRecipe(@PathVariable String recipe_id, @RequestBody ReqUpdateRecipeDTO dto) {
        if (recipeService.updateRecipe(recipe_id, dto) == null)
            return ResponseEntity.notFound().build();
        else return ResponseEntity.status(HttpStatus.OK).body(recipeService.updateRecipe(recipe_id, dto));
    }

    @GetMapping("/{recipe_id}")
    public ResponseEntity<RespGetRecipeByIdDTO> getRecipeById(@PathVariable String recipe_id) {
        if (recipeService.getRecipeByID(recipe_id).isEmpty())
            return ResponseEntity.notFound().build();
        else return ResponseEntity.status(HttpStatus.FOUND).body(recipeService.getRecipeByID(recipe_id).get());
    }

    @DeleteMapping("/{recipe_id}")
    public ResponseEntity<RespDeleteRecipeDTO> deleteRecipeById(@PathVariable String recipe_id) { //Not working
        if (recipeService.deleteRecipeById(recipe_id) == null)
            return ResponseEntity.notFound().build();
        else return ResponseEntity.status(HttpStatus.OK).body(recipeService.deleteRecipeById(recipe_id));
    }
}
