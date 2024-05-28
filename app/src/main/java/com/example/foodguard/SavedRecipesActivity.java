package com.example.foodguard;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SavedRecipesActivity extends AppCompatActivity {

    private static final String TAG = "SavedRecipesActivity";

    private RecyclerView recyclerView;
    private SuggestedRecipesAdapter adapter;
    private List<Recipe> recipeList;
    private FloatingActionButton fab;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_recipes);

        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recipeList = new ArrayList<>();
        adapter = new SuggestedRecipesAdapter(recipeList, this);
        recyclerView.setAdapter(adapter);

        loadSavedRecipes();

        fab.setOnClickListener(view -> {
            Intent intent = new Intent(SavedRecipesActivity.this, AddIngredientsActivity.class);
            startActivity(intent);
        });

        setupBottomNavigationView();
    }

    private void loadSavedRecipes() {
        try {
            SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
            Set<String> favorites = preferences.getStringSet("favorites", new HashSet<>());

            Gson gson = new Gson();
            Type type = new TypeToken<Recipe>() {}.getType();

            for (String json : favorites) {
                Recipe recipe = gson.fromJson(json, type);
                recipeList.add(recipe);
            }

            adapter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.e(TAG, "Error loading saved recipes: ", e);
        }
    }

    private void setupBottomNavigationView() {
        try {
            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
            bottomNavigationView.setSelectedItemId(R.id.saved);

            bottomNavigationView.setOnItemSelectedListener(item -> {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                        return true;
                    case R.id.suggested:
                        startActivity(new Intent(getApplicationContext(), SuggestedRecipesActivity.class));
                        finish();
                        return true;
                    case R.id.add:
                        startActivity(new Intent(getApplicationContext(), AddIngredientsActivity.class));
                        finish();
                        return true;
                    case R.id.saved:
                        return true;
                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        finish();
                        return true;
                }
                return false;
            });
        } catch (Exception e) {
            Log.e(TAG, "Error setting up BottomNavigationView: ", e);
        }
    }
}
