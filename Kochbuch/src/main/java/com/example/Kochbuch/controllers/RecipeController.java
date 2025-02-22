package com.example.Kochbuch.controllers;

import com.example.Kochbuch.dtos.*;
import com.example.Kochbuch.services.RecipeService;
import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/kochbuch/recipe")
public class RecipeController {
    RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
    @PostMapping
    public ResponseEntity<RespCreateNewRecipeDTO> createNewRecipe(@RequestBody @Validated ReqCreateNewRecipeDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(recipeService.createNewRecipe(dto));
    }
//    @PutMapping("/{recipe_id}")
//    public ResponseEntity<RespFillRecipeDTO> fillCreatedRecipe(@PathVariable long recipe_id, @RequestBody ReqFillRecipeDTO dto){
//        return ResponseEntity.status(HttpStatus.OK).body(recipeService.fillCreatedRecipe(recipe_id,dto));
//    }
    @GetMapping("/{recipe_id}")
    public ResponseEntity<RespGetRecipeByIdDTO> getRecipeById(@PathVariable long recipe_id){
       return ResponseEntity.status(HttpStatus.OK).body(recipeService.getRecipeByID(recipe_id));
    }
//    @DeleteMapping("/recipe_id")
//    public void deleteRecipeById(@PathVariable long recipe_id){
//        ResponseEntity.ok(recipeService.deleteRecipeById(recipe_id));
//    }
}
