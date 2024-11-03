package com.example.persistencetemplate.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.persistencetemplate.EditStudentActivity;
import com.example.persistencetemplate.R;
import com.example.persistencetemplate.model.StudentModel;

import java.util.List;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentHolder> {
    private Context context;
    private List<StudentModel> studentList;

    public StudentAdapter(List<StudentModel> studentList){
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public StudentAdapter.StudentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_student, parent, false);

        return new StudentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.StudentHolder holder, int position) {
        StudentModel studentData = studentList.get(position);
        String name = studentData.getName();
        String nim = studentData.getNim();

        holder.itemNameTV.setText(name);
        holder.itemNimTV.setText(nim);

        holder.itemStudentLL.setOnClickListener(v->{

        });
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public class StudentHolder extends RecyclerView.ViewHolder {
        TextView itemNameTV, itemNimTV;
        LinearLayout itemStudentLL;

        public StudentHolder(@NonNull View itemView) {
            super(itemView);

            itemNameTV = itemView.findViewById(R.id.itemNameTV);
            itemNimTV = itemView.findViewById(R.id.itemNimTV);
            itemStudentLL = itemView.findViewById(R.id.itemStudentLL);
        }
    }
}
