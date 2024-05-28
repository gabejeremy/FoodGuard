package com.example.foodguard;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SuggestedRecipesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SuggestedRecipesAdapter adapter;
    private List<Recipe> recipeList;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_recipes);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recipeList = new ArrayList<>();
        adapter = new SuggestedRecipesAdapter(recipeList);
        recyclerView.setAdapter(adapter);

        // Replace this with your Firestore query or hardcoded recipes
        // For example, if you hardcoded recipes:
        Recipe recipe1 = new Recipe("Chicken Curry", Arrays.asList("Chicken", "Curry Powder", "Onion", "Tomato"), 4.5);
        Recipe recipe2 = new Recipe("Vegetable Stir-Fry", Arrays.asList("Broccoli", "Carrot", "Bell Pepper", "Soy Sauce"), 4.7);
        Recipe recipe3 = new Recipe("Sisig", Arrays.asList("Pig Mascara", "Garlic", "Soy Sauce", "Calamansi"), 4.9);
        Recipe recipe4 = new Recipe("Pasta Carbonara", Arrays.asList("Spaghetti", "Bacon", "Egg", "Parmesan Cheese"), 4.4);
        Recipe recipe5 = new Recipe("Sinigang", Arrays.asList("Pork Belly", "Eggplant", "Water Spinach", "Sinigang Mix"), 4.6);
        Recipe recipe6 = new Recipe("Chickaen Wingz", Arrays.asList("Chicken Wings", "Butter", "Garlic Powder", "Salt"), 5.0);

        // Add the recipes to the list
        recipeList.add(recipe1);
        recipeList.add(recipe2);
        recipeList.add(recipe3);
        recipeList.add(recipe4);
        recipeList.add(recipe5);
        recipeList.add(recipe6);

        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged();

        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username_key", "username");
        editor.apply();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.suggested);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                        finish();
                        return true;
                    case R.id.suggested:
                        return true;
                    case R.id.add:
                        startActivity(new Intent(getApplicationContext(), AddIngredientsActivity.class));
                        //   overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
                }
                return false;
            }
        });

        fab = findViewById(R.id.fab); // Initialize fab before setting click listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the activity you want to open when the FAB is clicked
                Intent intent = new Intent(SuggestedRecipesActivity.this, AddIngredientsActivity.class);
                startActivity(intent);
            }
        });

    }
}
