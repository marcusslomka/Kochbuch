package com.example.Kochbuch.services;

import com.example.Kochbuch.db.daos.IngredientDAO;
import com.example.Kochbuch.db.daos.RecipeIngredientDAO;
import com.example.Kochbuch.dtos.*;
import com.example.Kochbuch.entities.Ingredient;
import com.example.Kochbuch.entities.Recipe;
import com.example.Kochbuch.db.daos.RecipeDAO;
import com.example.Kochbuch.entities.RecipeIngredient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {
    RecipeDAO recipeDAO;
    IngredientDAO ingredientDAO;
    RecipeIngredientDAO recipeIngredientDAO;

    public RecipeService(RecipeDAO recipeDAO, IngredientDAO ingredientDAO, RecipeIngredientDAO recipeIngredientDAO) {
        this.recipeDAO = recipeDAO;
        this.ingredientDAO = ingredientDAO;
        this.recipeIngredientDAO = recipeIngredientDAO;

    }

    public RespCreateNewRecipeDTO createNewRecipe(ReqCreateNewRecipeDTO dto) {
        //Rezept erstellen + DB eintrag
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.title());
        recipe.setDescription(dto.description());
        recipe.setId(this.recipeDAO.generateID(recipe));
        this.recipeDAO.save(recipe);
        //Zutaten aus dem DTO durchgehen und dem Rezept zuordnen
        assignIngredients(dto, recipe);
        this.recipeDAO.update(recipe);
        return new RespCreateNewRecipeDTO(recipe.getTitle(), recipe.getId());
    }

    public void assignIngredients(ReqCreateNewRecipeDTO dto, Recipe recipe) {
        dto.ingredients().stream()
                .map(RecipeIngredientsDTO -> {
                    Ingredient ingredient = (Ingredient) Optional.empty()
                            .orElseGet(() -> {
                                //Neue Zutat anlegen, falls noch nciht vorhanden
                                return createNewIngredient(RecipeIngredientsDTO);
                            });
                    return createNewRecipeIngredient(RecipeIngredientsDTO, recipe, ingredient);
                });
    }

    public Ingredient createNewIngredient(RecipeIngredientDTO dto) {
        Ingredient newIngredient = new Ingredient();
        newIngredient.setID(this.ingredientDAO.generateId(newIngredient));
        newIngredient.setCategory(dto.categorie());
        newIngredient.setName(dto.name());
        this.ingredientDAO.save(newIngredient);
        return newIngredient;
    }

    public RecipeIngredient createNewRecipeIngredient(RecipeIngredientDTO dto, Recipe recipe, Ingredient ingredient) {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setAmount(dto.amount());
        recipeIngredient.setQuantityUnit(dto.quantityUnit());
        this.recipeIngredientDAO.save(recipeIngredient);
        return recipeIngredient;
    }

    public Optional<RespGetRecipeByIdDTO> getRecipeByID(String id) {
        Optional<Recipe> toGetRecipe = Optional.ofNullable(recipeDAO.findById(id));
        return toGetRecipe.map(recipe -> new RespGetRecipeByIdDTO(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getIngredients()));
    }

    public RespUpdateRecipeDTO updateRecipe(String id, ReqUpdateRecipeDTO dto) {
        Optional<Recipe> optionalRecipe = Optional.ofNullable(recipeDAO.findById(id));
        if (optionalRecipe.isEmpty())
            return null;
        else {
            Recipe toUpdateRecipe = optionalRecipe.get();
            toUpdateRecipe.setTitle(dto.title());
            toUpdateRecipe.setDescription(dto.description());
            toUpdateRecipe.setIngredients(dto.ingredients());
            this.recipeDAO.update(toUpdateRecipe);
            return new RespUpdateRecipeDTO(toUpdateRecipe.getTitle(), toUpdateRecipe.getId());
        }
    }

    public RespDeleteRecipeDTO deleteRecipeById(String id) {
        Recipe toDeleteRecipe = recipeDAO.findById(id);
        if (toDeleteRecipe == null)
            return null;
        else {
            recipeDAO.deleteById(id);
            return new RespDeleteRecipeDTO(toDeleteRecipe.getId(), toDeleteRecipe.getTitle());
        }
    }
}
