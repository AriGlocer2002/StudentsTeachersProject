package com.example.studentsteachersproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class allStudentsScreen extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lv_all_students;
    studentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_students);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);


        lv_all_students = findViewById(R.id.lv_all_students);


        ArrayList<Student> students =new ArrayList<>();

        getAllStudents(students);


        adapter= new studentAdapter(students, allStudentsScreen.this);
        lv_all_students.setAdapter(adapter);
    }

    public ArrayList<Student> getAllStudents (ArrayList<Student> students){

        int studentId = 0;
        String name = "";
        String lname = "";
        String classroom = "";
        int average = 0;

        Cursor tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_STUDENT, null);
        while(tmp.moveToNext()) {

            studentId = tmp.getInt(0);
            name = tmp.getString(1);
            lname = tmp.getString(2);
            classroom = tmp.getString(3);
            average = tmp.getInt(4);

            Student student = new Student(studentId, name, lname, classroom, average);
            students.add(student);
        }
        tmp.close();

        return students;
    }

}
