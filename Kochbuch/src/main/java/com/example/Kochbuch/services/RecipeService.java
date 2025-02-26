package com.example.Kochbuch.services;

import com.example.Kochbuch.dtos.*;
import com.example.Kochbuch.entities.Recipe;
import com.example.Kochbuch.db.RecipeDAO;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    RecipeDAO recipeDAO;

    public RecipeService(RecipeDAO recipeDAO) {
        this.recipeDAO = recipeDAO;

    }

    public RespCreateNewRecipeDTO createNewRecipe(ReqCreateNewRecipeDTO dto){
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.title());
        recipe.setDescription(dto.description());
        //Zutaten durchgehen und zuordnen
        /* List<RecipeIngredients> recipeIngredients = dto.ingredients().stream()
                .map(RecipeIngredientsDTO ->{
                    Ingredient ingredient = Optional.ofNullable(null)
                            .orElseGet(()->{
                                //Neue Zutat anlegen, falls noch nciht vorhanden
                                Ingredient newIngredient = new Ingredient();
                                newIngredient.setCategory(RecipeIngredientsDTO.categorie());
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
        */
        //Liste an Rezeptzutaten dem Rezept noch zuornden
        recipe.setIngredients(List.of());

        recipe.setId(this.recipeDAO.save(recipe));
        return new RespCreateNewRecipeDTO(recipe.getTitle(), recipe.getId());
    }

    public RespGetRecipeByIdDTO getRecipeByID (String id){
        Optional<Recipe> toGetRecipe = Optional.ofNullable(recipeDAO.findById(id));
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
  /*  public void deleteRecipeById(long id){
        if (recipeRepository.findById(id).isEmpty())
            throw new IllegalArgumentException("Recipe with this ID doesnt exists");
        else{
            recipeRepository.deleteById(id);
        }
    }
    */

}
