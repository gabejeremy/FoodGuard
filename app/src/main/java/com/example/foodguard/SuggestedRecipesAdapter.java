package com.example.foodguard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class SuggestedRecipesAdapter extends RecyclerView.Adapter<SuggestedRecipesAdapter.RecipeViewHolder> {

    private List<Recipe> recipeList;

    public SuggestedRecipesAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    static class RecipeViewHolder extends RecyclerView.ViewHolder {
        private TextView recipeName;
        private TextView ingredientsList;

        RecipeViewHolder(@NonNull View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeNameTextView);
            ingredientsList = itemView.findViewById(R.id.recipeDescriptionTextView);
        }

        void bind(Recipe recipe) {
            recipeName.setText(recipe.getDishName());
            // Join ingredients list into a single string
            String ingredients = android.text.TextUtils.join(", ", recipe.getDishIngredients());
            ingredientsList.setText(ingredients);
        }
    }
}
