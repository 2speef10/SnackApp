package com.example.snackapp.Views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


import com.example.snackapp.Model.DatabaseHelper;
import com.example.snackapp.Model.MainActivity;

import com.example.snackapp.Model.User;
import com.example.snackapp.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SignupActivity extends AppCompatActivity {
    private DatabaseHelper Database;
    private ExecutorService executorService;

    private EditText emailEditText, passwordEditText, confirmPasswordEditText,
            firstNameEditText, lastNameEditText, phoneNumberEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Vind de invoervelden door hun id's
        emailEditText = findViewById(R.id.signup_email);
        passwordEditText = findViewById(R.id.signup_password);
        confirmPasswordEditText = findViewById(R.id.signup_confirm);
        firstNameEditText = findViewById(R.id.signup_firstName);
        lastNameEditText = findViewById(R.id.signup_lastName);
        phoneNumberEditText = findViewById(R.id.signup_phoneNumber);

        Button signupButton = findViewById(R.id.signup_button);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hier zal het registratieproces worden gestart
                registerUser();
            }
        });

        TextView loginRedirectText = findViewById(R.id.loginRedirectText);
        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToLogin(); // Functie om naar de loginpagina te navigeren
            }
        });
    }

    private void registerUser() {
        // Haal gegevens op uit de invoervelden
        Database = DatabaseHelper.getInstance(getApplicationContext());
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String phoneNumber = phoneNumberEditText.getText().toString();

        // Controleer of alle velden zijn ingevuld voordat je doorgaat met registreren
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password) || TextUtils.isEmpty(confirmPassword)
                || TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || TextUtils.isEmpty(phoneNumber)) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Controleer of de wachtwoorden overeenkomen
        if (password.equals(confirmPassword)) {
            // Maak een User-object aan met de ingevoerde gegevens
            User newUser = new User(email, password, firstName, lastName, phoneNumber);
            executorService = Executors.newFixedThreadPool(2);

            executorService.execute(()-> {
                Database.getUserDao().insertUser(newUser);
            });


            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();

            // Start MainActivity na succesvolle registratie
            Intent intent = new Intent(SignupActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Sluit de SignupActivity om terugkeren te voorkomen
        } else {
            // Geef een foutmelding als de wachtwoorden niet overeenkomen
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
        }
    }

    private void navigateToLogin() {
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Sluit de huidige activiteit (SignupActivity) na navigatie
    }
}
