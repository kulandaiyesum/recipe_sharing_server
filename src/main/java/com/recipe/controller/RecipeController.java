package com.recipe.controller;

import com.recipe.model.Recipe;
import com.recipe.model.User;
import com.recipe.service.RecipeService;
import com.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {
    @Autowired
    private RecipeService recipeService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Recipe createRecipe(@RequestBody Recipe recipe, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return recipeService.createRecipe(recipe, user);

    }

    @GetMapping
    public List<Recipe> getAllRecipe() {
        return recipeService.findAllRecipe();
    }

    @PutMapping("/{recipeId}")
    public Recipe updateRecipe(@RequestBody Recipe recipe, @PathVariable long recipeId) throws Exception {
        return recipeService.updateRecipe(recipe, recipeId);
    }

    @DeleteMapping("/{recipeId}")
    public String deleteRecipe(@PathVariable Long recipeId) throws Exception {
        recipeService.deleteRecipe(recipeId);
        return "Recipe deleted successfully";
    }

    @PutMapping("/{recipeId}/like")
    public Recipe likeRecipe(@PathVariable Long recipeId, @RequestHeader("Authorization") String jwt) throws Exception {
        User user = userService.findUserByJwt(jwt);
        return recipeService.likeRecipe(recipeId, user);
    }
}
