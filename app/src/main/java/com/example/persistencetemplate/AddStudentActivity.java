package com.example.persistencetemplate;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.persistencetemplate.adapter.StudentAdapter;
import com.example.persistencetemplate.database.DBStudentManager;
import com.example.persistencetemplate.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {
    private EditText addNameET, addNimET;
    private Button addSubmitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_student);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addNameET = findViewById(R.id.addNameET);
        addNimET = findViewById(R.id.addNimET);
        addSubmitBtn = findViewById(R.id.addSubmitBtn);

        addSubmitBtn.setOnClickListener(v->{
            String name = addNameET.getText().toString();
            String nim = addNimET.getText().toString();

            DBStudentManager dbManager = new DBStudentManager(this);

            dbManager.open();
            dbManager.addStudent(name, nim);
            dbManager.close();

            Intent back = new Intent(this, HomeActivity.class);
            back.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            startActivity(back);

        });
    }



}