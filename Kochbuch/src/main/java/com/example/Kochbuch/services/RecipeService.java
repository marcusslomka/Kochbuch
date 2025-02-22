package com.example.Kochbuch.services;

import com.example.Kochbuch.dtos.*;
import com.example.Kochbuch.entities.Ingredient;
import com.example.Kochbuch.entities.Recipe;
import com.example.Kochbuch.entities.RecipeIngredients;
import com.example.Kochbuch.repositories.IngredientRepository;
import com.example.Kochbuch.repositories.RecipeRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeService {
    RecipeRepository recipeRepository;
    IngredientRepository ingredientRepository;

    public RecipeService(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public RespCreateNewRecipeDTO createNewRecipe(ReqCreateNewRecipeDTO dto){
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.title());
        recipe.setDescription(dto.description());
        //Zutaten durchgehen und zuordnen
        List<RecipeIngredients> recipeIngredients = dto.ingredients().stream()
                .map(RecipeIngredientsDTO ->{
                    Ingredient ingredient = ingredientRepository.findByName(RecipeIngredientsDTO.name())
                            .orElseGet(()->{
                                //Neue Zutat anlegen, falls noch nciht vorhanden
                                Ingredient newIngredient = new Ingredient();
                                newIngredient.setCategorie(RecipeIngredientsDTO.categorie());
                                newIngredient.setName(RecipeIngredientsDTO.name());
                                ingredientRepository.save(newIngredient);
                                return newIngredient;
                                    });
                    RecipeIngredients recipeIngredient = new RecipeIngredients();
                    recipeIngredient.setIngredient(ingredient);
                    recipeIngredient.setAmount(RecipeIngredientsDTO.amount());
                    recipeIngredient.setQuantityUnit(RecipeIngredientsDTO.quantityUnit());
                    //wirft infinityloop, wenns in der Entity nicht mit JasonIgnore gekennzeichnet wird
                    recipeIngredient.setRecipe(recipe);
                    return recipeIngredient;
                }).toList();

        //Liste an Rezeptzutaten dem Rezept noch zuornden
        recipe.setIngredients(recipeIngredients);
        this.recipeRepository.save(recipe);
        return new RespCreateNewRecipeDTO(recipe.getId(),recipe.getTitle());
    }

    public RespGetRecipeByIdDTO getRecipeByID(long id){
        Optional<Recipe> toGetRecipe = recipeRepository.findById(id);
        if (toGetRecipe.isEmpty()){
            throw new IllegalArgumentException("No Recipe with this ID found");
        }
        else {
            return new RespGetRecipeByIdDTO(
                    toGetRecipe.get().getId(),
                    toGetRecipe.get().getTitle(),
                    toGetRecipe.get().getDescription(),
                    toGetRecipe.get().getIngredients()) ;
        }
    }

//    public RespFillRecipeDTO updateRecipe(long id, ReqFillRecipeDTO dto){
//        Optional<Recipe> optionalRecipe = recipeRepository.findById(id);
//        if (optionalRecipe.isEmpty())
//            throw new IllegalArgumentException("No Recipe with this ID found");
//        else {
//            Recipe toFillRecipe = optionalRecipe.get();
//            toFillRecipe.setDescription(dto.description());
//            toFillRecipe.setIngredients(dto.ingredients());
//            this.recipeRepository.save(toFillRecipe);
//            return new RespFillRecipeDTO(toFillRecipe.getTitle());
//        }
//
//    }
    public void deleteRecipeById(long id){
        if (recipeRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("Recipe with this ID doesnt exists");
        else{
            recipeRepository.deleteById(id);
        }
    }
}
