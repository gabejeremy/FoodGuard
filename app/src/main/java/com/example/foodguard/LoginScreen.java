package com.example.foodguard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {

    EditText email, password;
    Button loginButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mAuth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(em)) {
                    Toast.makeText(LoginScreen.this, "Enter Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(LoginScreen.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(em, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginScreen.this, "Authentication success!",
                                            Toast.LENGTH_SHORT).show();
                                    // Redirect to Main Activity
                                    startActivity(new Intent(LoginScreen.this, MainActivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(LoginScreen.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public void openSignUpActivity(View view) {
        Intent toSignUp = new Intent(this, SignUpActivity.class);
        startActivity(toSignUp);
    }
}
