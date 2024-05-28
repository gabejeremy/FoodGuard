package com.example.foodguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SuggestedRecipesAdapter extends RecyclerView.Adapter<SuggestedRecipesAdapter.ViewHolder> {
    private static final String TAG = "SavedRecipesActivity";

    private List<Recipe> recipeList;
    private Context context;

    public SuggestedRecipesAdapter(List<Recipe> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    public interface RecipeRemovalListener {
        void onRecipeRemoved(int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.recipeName.setText(recipe.getDishName());
        // Set other fields here...

        // Set an onClickListener for the favorite button
        holder.favoriteButton.setOnClickListener(v -> {
            // Retrieve the recipe name from the TextView associated with this item
            String recipeName = holder.recipeName.getText().toString();
            // Now you have the recipe name, you can handle it as needed, such as saving it to favorites
            saveRecipeToFavorites(recipeName);
        });
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    private void saveRecipeToFavorites(String recipe) {
        try {
            // Retrieve the SharedPreferences instance
            SharedPreferences preferences = context.getSharedPreferences("my_preferences", Context.MODE_PRIVATE);
            // Get the set of existing favorites, or create a new empty set if it doesn't exist
            Set<String> favorites = preferences.getStringSet("favorites", new HashSet<>());
            // Serialize the Recipe object to JSON
            Gson gson = new Gson();
            String jsonRecipe = gson.toJson(recipe);
            // Add the JSON representation of the recipe to the favorites set
            favorites.add(jsonRecipe);
            // Update the SharedPreferences with the new set of favorites
            SharedPreferences.Editor editor = preferences.edit();
            editor.putStringSet("favorites", favorites);
            editor.apply();
            // Optionally, you can show a message indicating that the recipe was saved to favorites
            Toast.makeText(context, "Recipe added to favorites", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Error saving recipe to favorites: ", e);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView recipeName;
        public ImageButton favoriteButton;

        public ViewHolder(View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipeNameTextView);
            favoriteButton = itemView.findViewById(R.id.favorite);
        }
    }
}
