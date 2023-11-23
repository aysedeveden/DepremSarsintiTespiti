package com.example.sarsintitespiti1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {


    TextView btn;
    private EditText inputUsername, inputPassword, inputEmail, inputConformPassword;
    Button btnRegiter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btn = findViewById(R.id.alreadyHaveAccount);
        inputUsername = findViewById(R.id.inputUsername);
        inputPassword = findViewById(R.id.inputPassword);
        inputEmail = findViewById(R.id.inputEmail);
        inputConformPassword = findViewById(R.id.inputConformPassword);
        btnRegiter = findViewById(R.id.btnRegister);
        btnRegiter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkCrededantials();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }

    private void checkCrededantials() {
        String username = inputUsername.getText().toString();
        String password = inputPassword.getText().toString();
        String email = inputEmail.getText().toString();
        String conformPassword = inputConformPassword.getText().toString();
        if (username.isEmpty() || username.length() < 7) {
            showError(inputUsername, "Kullanıcı adınız geçerli değil!");
        } else if (email.isEmpty() || !email.contains("@")) {
            showError(inputEmail, "Geçerli bir email adresi giriniz!");
        } else if (password.isEmpty() || password.length() < 7) {
            showError(inputPassword, "Şifreniz 7 karakterden fazla olmalıdır!");
        } else if (conformPassword.isEmpty() || conformPassword.equals(password)) {
            showError(inputConformPassword, "Şifreler eşleşmiyor!");
        } else {
            Toast.makeText(this, "Call Register Method", Toast.LENGTH_SHORT).show();
        }
    }

    private void showError(EditText input, String s) {
        inputUsername.setError(s);
        input.setError(s);


    }
}