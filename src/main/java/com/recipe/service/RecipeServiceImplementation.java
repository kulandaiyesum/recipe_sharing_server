package com.recipe.service;

import com.recipe.model.Recipe;
import com.recipe.model.User;
import com.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeServiceImplementation implements RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Override
    public Recipe createRecipe(Recipe recipe, User user) {

        Recipe cratedRecipe = new Recipe();
        cratedRecipe.setTitle(recipe.getTitle());
        cratedRecipe.setDescription(recipe.getDescription());
        cratedRecipe.setImage(recipe.getImage());
        cratedRecipe.setUser(user);
        cratedRecipe.setVegetarian(recipe.isVegetarian());
        cratedRecipe.setCreatedAt(LocalDateTime.now());

        return recipeRepository.save(cratedRecipe);
    }

    @Override
    public Recipe findRecipeById(Long id) throws Exception {
        Optional<Recipe> opt = recipeRepository.findById(id);

        if (opt.isPresent()) {
            return opt.get();
        }
        throw new Exception("Recipe not found with id" + id);
    }

    @Override
    public void deleteRecipe(Long id) throws Exception {
        findRecipeById(id);
        recipeRepository.deleteById(id);
    }

    @Override
    public Recipe updateRecipe(Recipe recipe, Long id) throws Exception {
        Recipe oldRecipe = findRecipeById(id);
        if (recipe.getTitle() != null) {
            oldRecipe.setTitle(recipe.getTitle());
        }
        if (recipe.getImage() != null) {
            oldRecipe.setImage(recipe.getImage());
        }
        if (recipe.getDescription() != null) {
            oldRecipe.setDescription(recipe.getDescription());
        }
        oldRecipe.setVegetarian(recipe.isVegetarian());

        return recipeRepository.save(oldRecipe);
    }

    @Override
    public List<Recipe> findAllRecipe() {
        return recipeRepository.findAll();
    }

    @Override
    public Recipe likeRecipe(Long recipeId, User user) throws Exception {
        try
        {
            Recipe recipe = findRecipeById(recipeId);
            if (recipe.getLikes().contains(user.getId())) {
                recipe.getLikes().remove(user.getId());
            } else {
                recipe.getLikes().add(user.getId());
            }
            return recipeRepository.save(recipe);
        } catch (Exception e){
            System.out.println("ex"+ e);
        }
        return null;
    }
}
