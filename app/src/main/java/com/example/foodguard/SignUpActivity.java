package com.example.foodguard;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText username, password, confirmPassword, email;
    Button signupButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        email = findViewById(R.id.email);
        signupButton = findViewById(R.id.signupButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String conPass = confirmPassword.getText().toString();
                String em = email.getText().toString();

                if (TextUtils.isEmpty(user)) {
                    Toast.makeText(SignUpActivity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(SignUpActivity.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(conPass)) {
                    Toast.makeText(SignUpActivity.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(em)) {
                    Toast.makeText(SignUpActivity.this, "Enter Email Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!pass.equals(conPass)) {
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(em, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(SignUpActivity.this, "Authentication success.",
                                            Toast.LENGTH_SHORT).show();
                                    // Redirect to Login screen or Main Activity
                                    startActivity(new Intent(SignUpActivity.this, LoginScreen.class));
                                    finish();
                                } else {
                                    Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }

    public void openLoginActivity(View view) {
        Intent toLogin = new Intent(this, LoginScreen.class);
        startActivity(toLogin);
        finish();
    }
}
