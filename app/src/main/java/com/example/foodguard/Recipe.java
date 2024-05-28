package com.example.foodguard;

import java.util.List;

public class Recipe {
    private String dishName;
    private List<String> dishIngredients;
    private double foodGuardRating;

    public Recipe() {
        // Default constructor required for Firestore
    }

    public Recipe(String dishName, List<String> dishIngredients, double foodGuardRating) {
        this.dishName = dishName;
        this.dishIngredients = dishIngredients;
        this.foodGuardRating = foodGuardRating;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public List<String> getDishIngredients() {
        return dishIngredients;
    }

    public void setDishIngredients(List<String> dishIngredients) {
        this.dishIngredients = dishIngredients;
    }

    public double getFoodGuardRating() {
        return foodGuardRating;
    }

    public void setFoodGuardRating(double foodGuardRating) {
        this.foodGuardRating = foodGuardRating;
    }
}