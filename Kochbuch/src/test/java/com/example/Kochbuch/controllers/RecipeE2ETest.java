package com.example.Kochbuch.controllers;

import com.example.Kochbuch.dtos.ReqCreateNewRecipeDTO;
import com.example.Kochbuch.dtos.RespCreateNewRecipeDTO;
import com.example.Kochbuch.dtos.RespGetRecipeByIdDTO;
import com.example.Kochbuch.entities.Recipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RecipeE2ETest {
    @LocalServerPort
    int port;

    @Test
    @DisplayName("Recipe Created Successfully")
    public void recipeCreatedSuccessfully() {
        //Http-Endpunkt anfragen mit der ID, um bestehen der ID und des Rezeptes zu verifizieren
        var client = RestClient.create("http://localhost:" + port + "/api/v1/kochbuch/recipe");
        var recipe = new ReqCreateNewRecipeDTO("Pizza", "Ist lecker", List.of());

        var response = client.post().body(recipe).retrieve().toEntity(RespCreateNewRecipeDTO.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody().title()).isEqualTo("Pizza");
        assertThat(response.getBody().id()).isNotEmpty();

        var recipeByID = client.get().uri("/" + response.getBody().id()).retrieve().toEntity(RespGetRecipeByIdDTO.class);
        assertThat(recipeByID.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        assertThat(recipeByID.getBody()).isEqualTo(new RespGetRecipeByIdDTO(response.getBody().id(),"Pizza","Ist lecker",List.of()));
    }

    @Test
    @DisplayName("Recipe Updated Successfully")
    public void recipeUpdatedSucessfully(){
        var client = RestClient.create("http://localhost:" + port + "/api/v1/kochbuch/recipe");
        var recipe = new ReqCreateNewRecipeDTO("neue Pizza ist besser", "Ist besser", List.of());

    }

}