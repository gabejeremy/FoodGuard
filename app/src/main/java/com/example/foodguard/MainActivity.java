package com.example.foodguard;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodguard.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()) {
                case R.id.home:
                    return true;

                case R.id.suggested:
                    startActivity(new Intent(getApplicationContext(), SuggestedRecipesActivity.class));
                 //   overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;

                case R.id.add:
                    startActivity(new Intent(getApplicationContext(), AddIngredientsActivity.class));
                 //   overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;

                case R.id.saved:
                    startActivity(new Intent(getApplicationContext(), SavedRecipesActivity.class));
                  //  overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;


                case R.id.profile:
                    startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
               //     overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;

            }
            return false;
        });
    }

}