package com.example.persistencetemplate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.persistencetemplate.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class DBStudentManager {
    private DBHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBStudentManager(Context c){
        context = c;
    }

    public DBStudentManager open(){
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();

    }

    public void addStudent(String name, String nim){
//        String query = "INSERT INTO " + DBHelper.TABLE_STUDENT
//                + "(" + DBHelper.FIELD_NAME + ", " + DBHelper.FIELD_NIM + ") "
//                + "VALUES(" + name + ", " + nim + ")";
//        database.rawQuery(rawQuery, null);

        ContentValues values = new ContentValues();
        values.put(DBHelper.FIELD_NAME, name);
        values.put(DBHelper.FIELD_NIM, nim);
        database.insert(DBHelper.TABLE_STUDENT, null, values);

    }

    public List<StudentModel> getAllStudent(){
        List<StudentModel> listStudent = new ArrayList<>();

        String[] columns = new String[] {DBHelper.FIELD_ID, DBHelper.FIELD_NAME, DBHelper.FIELD_NIM};

        Cursor cursor = database.query(DBHelper.TABLE_STUDENT, columns, null, null, null, null, null);

        if(cursor.getCount() > 0){
            if(cursor.moveToFirst()){
                do{
                    int id = Integer.parseInt(cursor.getString(0));
                    String name = cursor.getString(1);
                    String nim = cursor.getString(2);
                    listStudent.add(new StudentModel(id, name, nim));
                }while (cursor.moveToNext());
            }
        }

        database.update();
        database.delete();

        return listStudent;
    }
}
