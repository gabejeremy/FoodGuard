package com.example.foodguard;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddIngredientsActivity extends AppCompatActivity {

    private EditText ingredientName;
    private DatePicker dateBought;
    private EditText quantity;
    private Spinner categorySpinner;
    private Button saveButton;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);
        setContentView(R.layout.activity_add_ingredients);

        ingredientName = findViewById(R.id.ingredientName);
        dateBought = findViewById(R.id.dateBought);
        quantity = findViewById(R.id.quantity);
        categorySpinner = findViewById(R.id.categorySpinner);
        saveButton = findViewById(R.id.saveButton);
        db = FirebaseFirestore.getInstance();

        // Set up category Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Initialize bottom navigation view
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
                        // Current Activity
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

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveIngredient();
            }
        });

        // Initialize dateBought with the current date
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Set the minimum date for the DatePicker to 5 years ago
        calendar.add(Calendar.YEAR, -5);
        dateBought.setMinDate(calendar.getTimeInMillis());

        dateBought.init(year, month, day, null);
    }

    private void saveIngredient() {
        String name = ingredientName.getText().toString().trim();
        int year = dateBought.getYear();
        int month = dateBought.getMonth();
        int day = dateBought.getDayOfMonth();


        // Calculate the actual year
        int actualYear = year - 1900;

        int quantityValue;
        try {
            quantityValue = Integer.parseInt(quantity.getText().toString().trim());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Please enter a valid quantity", Toast.LENGTH_SHORT).show();
            return;
        }

        String category = categorySpinner.getSelectedItem().toString();

        if (name.isEmpty()) {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
            return;
        }

        Map<String, Object> ingredient = new HashMap<>();
        ingredient.put("name", name);
        ingredient.put("dateBought", new Date(actualYear, month, day)); // Use the calculated actual year
        ingredient.put("quantity", quantityValue);
        ingredient.put("category", category);

        db.collection("ingredients")
                .add(ingredient)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(AddIngredientsActivity.this, "Ingredient saved", Toast.LENGTH_SHORT).show();
                    finish();
                })
                .addOnFailureListener(e -> Toast.makeText(AddIngredientsActivity.this, "Error saving ingredient", Toast.LENGTH_SHORT).show());
    }
}
