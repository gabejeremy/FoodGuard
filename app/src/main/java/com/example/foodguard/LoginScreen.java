package com.example.foodguard;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginScreen extends AppCompatActivity {

    EditText username, email;
    EditText password;
    Button loginButton;

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        mAuth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

//        loginButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if() {
//                    Toast.makeText(LoginScreen.this, "Login Successful!", Toast.LENGTH_SHORT).show();
//
//                        mAuth.signInWithEmailAndPassword(email, password)
//                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                        if (task.isSuccessful()) {
//                                            // Sign in success, update UI with the signed-in user's information
//                                            Toast.makeText(SignUpActivity.this, "Authentication success!.",
//                                                    Toast.LENGTH_SHORT).show();
//                                        } else {
//                                            // If sign in fails, display a message to the user.
//                                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
//                                                    Toast.LENGTH_SHORT).show();
//                                        }
//                                    }
//                                });
//
//                        /*Intent toMain = new Intent(LoginScreen.this, MainActivity.class);
//                        startActivity(toMain);*/
//                } else {
//                    Toast.makeText(LoginScreen.this, "Login Failed!", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    public void openSignUpActivity(View view) {
        Intent toSignUp = new Intent(this, SignUpActivity.class);
        startActivity(toSignUp);
    }
}
