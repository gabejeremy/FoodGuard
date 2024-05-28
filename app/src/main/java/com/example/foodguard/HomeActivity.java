package com.example.foodguard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    private FloatingActionButton fab;
    private ImageButton btnVeg;
    private ImageButton btnFruit;
    private ImageButton btnMeat;
    private ImageButton btnSauce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnVeg = findViewById(R.id.btnVeg);
        btnFruit = findViewById(R.id.btnFruit);
        btnSauce = findViewById(R.id.btnSauce);
        btnMeat = findViewById(R.id.btnMeat);

        btnVeg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent veg = new Intent(HomeActivity.this, DisplayIngredientsActivity.class);
                veg.putExtra("category", "Veggies");
                startActivity(veg);
            }
        });

        btnFruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fruit = new Intent(HomeActivity.this, DisplayIngredientsActivity.class);
                fruit.putExtra("category", "Fruits");
                startActivity(fruit);
            }
        });

        btnSauce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sauce = new Intent(HomeActivity.this, DisplayIngredientsActivity.class);
                sauce.putExtra("category", "Condiments");
                startActivity(sauce);
            }
        });

        btnMeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent meat = new Intent(HomeActivity.this, DisplayIngredientsActivity.class);
                meat.putExtra("category", "Meats");
                startActivity(meat);
            }
        });

        fab = findViewById(R.id.fab);

        // Retrieve the username from SharedPreferences (replace "username_key" with your actual key)
        // Save the username to SharedPreferences
        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username_key", "username"); // Replace "YourUsernameHere" with the actual username
        editor.apply();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        // Current Activity
                        return true;
                    case R.id.suggested:
                        startActivity(new Intent(getApplicationContext(), SuggestedRecipesActivity.class));
                        finish();
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the activity you want to open when the FAB is clicked
                Intent intent = new Intent(HomeActivity.this, AddIngredientsActivity.class);
                startActivity(intent);
            }
        });
    }
}