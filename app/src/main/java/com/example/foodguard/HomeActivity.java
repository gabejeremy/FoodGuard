package com.example.foodguard;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    private TextView welcomeTextView;
    private ImageView userProfileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize Views
        welcomeTextView = findViewById(R.id.welcomeTextView);

        SharedPreferences preferences = getSharedPreferences("my_preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("username_key", "user");
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
    }
}
