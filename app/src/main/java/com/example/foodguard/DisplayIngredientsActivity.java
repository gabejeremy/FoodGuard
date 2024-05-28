package com.example.foodguard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DisplayIngredientsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private IngredientsAdapter adapter;
    private List<Ingredient> ingredientList;
    private TextView emptyTextView;
    private Button btnBack;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ingredients);
        btnBack = findViewById(R.id.backButton);
        fab = findViewById(R.id.fab);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        emptyTextView = findViewById(R.id.emptyTextView);

        ingredientList = new ArrayList<>();
        adapter = new IngredientsAdapter(ingredientList);
        recyclerView.setAdapter(adapter);

        // Retrieve the category from the intent
        String category = getIntent().getStringExtra("category");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("ingredients")
                .whereEqualTo("category", category)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Ingredient ingredient = document.toObject(Ingredient.class);
                            ingredientList.add(ingredient);
                        }
                        if (ingredientList.isEmpty()) {
                            recyclerView.setVisibility(View.GONE);
                            emptyTextView.setVisibility(View.VISIBLE);
                        } else {
                            recyclerView.setVisibility(View.VISIBLE);
                            emptyTextView.setVisibility(View.GONE);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        recyclerView.setVisibility(View.GONE);
                        emptyTextView.setVisibility(View.VISIBLE);
                    }
                });

        btnBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DisplayIngredientsActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username_key", "username");
        editor.apply();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        return true;
                    case R.id.suggested:
                        startActivity(new Intent(getApplicationContext(), SuggestedRecipesActivity.class));
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
                }
                return false;
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the activity you want to open when the FAB is clicked
                Intent intent = new Intent(DisplayIngredientsActivity.this, AddIngredientsActivity.class);
                startActivity(intent);
            }
        });
    }
}

