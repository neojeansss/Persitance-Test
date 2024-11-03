package com.example.persistencetemplate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LoginActivity extends AppCompatActivity {
    private EditText loginUsernameET, loginPasswordET;
    private Button loginSubmitBtn;
    private SharedPreferences userPrefData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        loginUsernameET = findViewById(R.id.loginUsernameET);
        loginPasswordET = findViewById(R.id.loginPasswordET);
        loginSubmitBtn = findViewById(R.id.loginSubmitBtn);

        userPrefData = getSharedPreferences("user_preferences", Context.MODE_PRIVATE);
        checkUserLogin();
        loginSubmitBtn.setOnClickListener(v -> {
           String username = loginUsernameET.getText().toString();
           String password = loginPasswordET.getText().toString();

            SharedPreferences.Editor editor = userPrefData.edit();
            editor.putString("user_uname", username);
            editor.putString("user_password", password);
            editor.apply();
           
           Intent toHome = new Intent(this, HomeActivity.class);
           startActivity(toHome);
           finish();
                   
        });
    }

    private void checkUserLogin(){
        String username = userPrefData.getString("user_uname", "");
        if(!username.isEmpty()){
            Intent toHome = new Intent(this, HomeActivity.class);
            startActivity(toHome);
            finish();
        }
    }
}