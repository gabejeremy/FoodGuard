package com.example.foodguard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddIngredientsActivity extends AppCompatActivity {

    private EditText ingredientName, dateBought, quantity;
    private Button saveButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_ingredients);

        ingredientName = findViewById(R.id.ingredientName);
        dateBought = findViewById(R.id.dateBought);
        quantity = findViewById(R.id.quantity);
        saveButton = findViewById(R.id.saveButton);
        db = FirebaseFirestore.getInstance();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIngredient();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle FAB click
                startActivity(new Intent(AddIngredientsActivity.this, AddIngredientsActivity.class));
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.add);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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
    }

    private void saveIngredient() {
        String name = ingredientName.getText().toString().trim();
        String date = dateBought.getText().toString().trim();
        String qty = quantity.getText().toString().trim();

        if (name.isEmpty() || date.isEmpty() || qty.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> ingredient = new HashMap<>();
        ingredient.put("name", name);
        ingredient.put("dateBought", date);
        ingredient.put("quantity", qty);

        db.collection("ingredients")
                .add(ingredient)
                .addOnSuccessListener(documentReference -> Toast.makeText(AddIngredientsActivity.this, "Ingredient saved", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(AddIngredientsActivity.this, "Error saving ingredient", Toast.LENGTH_SHORT).show());
    }
}
