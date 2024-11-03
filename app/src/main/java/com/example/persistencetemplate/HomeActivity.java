package com.example.persistencetemplate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.persistencetemplate.adapter.StudentAdapter;
import com.example.persistencetemplate.database.DBStudentManager;
import com.example.persistencetemplate.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private ImageView homeLogoutIV, homeAddIV;
    private RecyclerView homeStudentRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        homeLogoutIV = findViewById(R.id.homeLogoutIV);
        homeAddIV = findViewById(R.id.homeAddIV);
        homeStudentRV = findViewById(R.id.homeStudentRV);

        loadStudentData();

        homeAddIV.setOnClickListener(v -> {
            startActivity(new Intent(this, AddStudentActivity.class));
        });

        homeLogoutIV.setOnClickListener(v -> {
            SharedPreferences userPrefData = getSharedPreferences("user_preferences", Context.MODE_PRIVATE);
            userPrefData.edit().clear().apply();

            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });
    }

    private void loadStudentData() {
        DBStudentManager dbManager = new DBStudentManager(this);

        List<StudentModel> studentList = new ArrayList<>();
        dbManager.open();
        studentList = dbManager.getAllStudent();
        dbManager.close();

        if (!studentList.isEmpty()) {
            StudentAdapter adapter = new StudentAdapter(studentList);
            homeStudentRV.setLayoutManager(new LinearLayoutManager((this)));
            homeStudentRV.setAdapter(adapter);
        }
    }
}