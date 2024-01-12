package com.example.snackapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.example.snackapp.Model.MainActivity;
import com.example.snackapp.R;

public class LoginActivity extends AppCompatActivity {

    private EditText emailEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailEditText = findViewById(R.id.login_email);
        passwordEditText = findViewById(R.id.login_password);

        Button loginButton = findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        TextView registerRedirectText = findViewById(R.id.loginRedirectText);
        registerRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToSignup();
            }
        });
    }

    private void loginUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // Hier moet je de logica voor het inloggen implementeren
        // Vergelijk bijvoorbeeld de inloggegevens met een database of een andere authenticatiemethode

        // Voorbeeld: controleer of de velden niet leeg zijn
        if (!email.isEmpty() && !password.isEmpty()) {
            // Voorbeeld: simulatie van succesvol inloggen
            Toast.makeText(this, "Login successful!", Toast.LENGTH_SHORT).show();

            // Start MainActivity na succesvol inloggen
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Sluit de LoginActivity om terugkeren te voorkomen
        } else {
            // Geef een foutmelding als de velden leeg zijn
            Toast.makeText(this, "Please enter valid credentials!", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToSignup() {
        Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
        startActivity(intent);
        finish(); // Sluit de huidige activiteit (LoginActivity) na navigatie
    }
}
