package com.example.foodguard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SuggestedRecipesAdapter adapter;
    private List<Recipe> recipeList;
    private FloatingActionButton fab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recipeList = new ArrayList<>();
        adapter = new SuggestedRecipesAdapter(recipeList, this);
        recyclerView.setAdapter(adapter);

        loadRecipes();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(SuggestedRecipesActivity.this, AddIngredientsActivity.class);
            startActivity(intent);
        });

        setupBottomNavigationView();
    }

    private void loadRecipes() {
        // Replace this with your Firestore query or hardcoded recipes
        // For example, if you hardcoded recipes:
        Recipe recipe1 = new Recipe("Chicken Curry", Arrays.asList("Chicken", "Curry Powder", "Onion", "Tomato"), 4.5);
        Recipe recipe2 = new Recipe("Vegetable Stir-Fry", Arrays.asList("Broccoli", "Carrot", "Bell Pepper", "Soy Sauce"), 4.7);
        Recipe recipe3 = new Recipe("Sisig", Arrays.asList("Pig Mascara", "Garlic", "Soy Sauce", "Calamansi"), 4.9);
        Recipe recipe4 = new Recipe("Pasta Carbonara", Arrays.asList("Spaghetti", "Bacon", "Egg", "Parmesan Cheese"), 4.4);
        Recipe recipe5 = new Recipe("Sinigang", Arrays.asList("Pork Belly", "Eggplant", "Water Spinach", "Sinigang Mix"), 4.6);
        Recipe recipe6 = new Recipe("Chicken Wings", Arrays.asList("Chicken Wings", "Butter", "Garlic Powder", "Salt"), 5.0);

        recipeList.add(recipe1);
        recipeList.add(recipe2);
        recipeList.add(recipe3);
        recipeList.add(recipe4);
        recipeList.add(recipe5);
        recipeList.add(recipe6);

        adapter.notifyDataSetChanged();
    }

    private void setupBottomNavigationView() {
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.suggested);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                    return true;
                case R.id.suggested:
                    return true;
                case R.id.add:
                    startActivity(new Intent(getApplicationContext(), AddIngredientsActivity.class));
                    finish();
                    return true;
                case R.id.saved:
                    startActivity(new Intent(getApplicationContext(), SavedRecipesActivity.class));
                    finish();
                    return true;
                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                    finish();
                    return true;
                default:
                    return false;
            }
        });
    }
}
