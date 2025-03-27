package com.example.Kochbuch.services;

import com.example.Kochbuch.db.daos.IngredientDAO;
import com.example.Kochbuch.dtos.*;
import com.example.Kochbuch.entities.Ingredient;
import com.example.Kochbuch.entities.Recipe;
import com.example.Kochbuch.db.daos.RecipeDAO;
import com.example.Kochbuch.entities.RecipeIngredient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    RecipeDAO recipeDAO;
    IngredientDAO ingredientDAO;

    public RecipeService(RecipeDAO recipeDAO,IngredientDAO ingredientDAO) {
        this.recipeDAO = recipeDAO;
        this.ingredientDAO =  ingredientDAO;

    }

    public RespCreateNewRecipeDTO createNewRecipe(ReqCreateNewRecipeDTO dto){
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.title());
        recipe.setDescription(dto.description());
        //Zutaten durchgehen und zuordnen
         List<RecipeIngredient> recipeIngredients = dto.ingredients().stream()
                .map(RecipeIngredientsDTO ->{
                    Ingredient ingredient = (Ingredient) Optional.ofNullable(null)
                            .orElseGet(()->{
                                //Neue Zutat anlegen, falls noch nciht vorhanden
                                Ingredient newIngredient = new Ingredient();
                                newIngredient.setCategory(RecipeIngredientsDTO.categorie());
                                newIngredient.setName(RecipeIngredientsDTO.name());
                                this.ingredientDAO.save(newIngredient);
                                return newIngredient;
                            });
                    RecipeIngredient recipeIngredient = new RecipeIngredient();
                    recipeIngredient.setIngredient(ingredient);
                    recipeIngredient.setAmount(RecipeIngredientsDTO.amount());
                    recipeIngredient.setQuantityUnit(RecipeIngredientsDTO.quantityUnit());
                    //wirft infinityloop, wenns in der Entity nicht mit JasonIgnore gekennzeichnet wird
                    recipeIngredient.setRecipe(recipe);
                    return recipeIngredient;
                }).toList();
        //Liste an Rezeptzutaten dem Rezept noch zuornden
        recipe.setId(this.recipeDAO.save(recipe));
        return new RespCreateNewRecipeDTO(recipe.getTitle(), recipe.getId());
    }

    public Optional<RespGetRecipeByIdDTO> getRecipeByID (String id){
        Optional<Recipe> toGetRecipe = Optional.ofNullable(recipeDAO.findById(id));
        return toGetRecipe.map(recipe -> new RespGetRecipeByIdDTO(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getIngredients()));
    }

    public RespUpdateRecipeDTO updateRecipe(String id, ReqUpdateRecipeDTO dto){
        Optional<Recipe> optionalRecipe = Optional.ofNullable(recipeDAO.findById(id));
        if (optionalRecipe.isEmpty())
            return null;
        else {
            Recipe toUpdateRecipe = optionalRecipe.get();
            toUpdateRecipe.setTitle(dto.title());
            toUpdateRecipe.setDescription(dto.description());
            toUpdateRecipe.setIngredients(dto.ingredients());
            this.recipeDAO.update(toUpdateRecipe);
            return new RespUpdateRecipeDTO(toUpdateRecipe.getTitle(),toUpdateRecipe.getId());
        }
    }

   public RespDeleteRecipeDTO deleteRecipeById(String id){
        Recipe toDeleteRecipe = recipeDAO.findById(id);
        if (toDeleteRecipe == null)
            return null;
        else{
            recipeDAO.deleteById(id);
            return new RespDeleteRecipeDTO(toDeleteRecipe.getId(),toDeleteRecipe.getTitle());
        }
    }
}
