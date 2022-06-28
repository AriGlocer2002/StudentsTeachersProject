package com.example.studentsteachersproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class searchByScreen extends AppCompatActivity {

    Intent intent;
    SQLiteDatabase db;
    ArrayList<Student> students;
    studentAdapter adapter;
    ListView lv_search_by;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        lv_search_by = findViewById(R.id.lv_search_by);

        intent = getIntent();
        String subject = intent.getStringExtra("subject");
        String classroom = intent.getStringExtra("classroom");
        int avg = intent.getIntExtra("avg", 0);
        String name = intent.getStringExtra("name");


        if(subject.isEmpty()){
            if(name.isEmpty()){
                students = getStudentsByAvgAndClassroom(avg, classroom);
            }else{
                students = getStudentsByNameAndClassroom(name, classroom);
            }
        }else{
            if(name.isEmpty()){
                students = getStudentsByAvgAndSubject(avg, subject);
            }else{
                students = getStudentsByNameAndSubject(name, subject);
            }
        }

        adapter = new studentAdapter(students, searchByScreen.this);
        lv_search_by.setAdapter(adapter);
    }

    public ArrayList<Student> getStudentsByAvgAndClassroom(int avg, String classroom){

        ArrayList<Student> students=new ArrayList<>();

        int studentId = 0;
        String stuName = "";
        String stuLastName = "";
        String stuClassName = "";
        int thisAvg = 0;


        Cursor tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_STUDENT+
                " WHERE "+Utils.TABLE_STUDENT_COL_CLASSNAME+" = '"+classroom+"'" +
                " AND "+ Utils.TABLE_STUDENT_COL_AVERAGE +" > " + avg, null);
        while (tmp.moveToNext()){
            studentId = tmp.getInt(0);
            stuName = tmp.getString(1);
            stuLastName = tmp.getString(2);
            stuClassName = tmp.getString(3);
            thisAvg = tmp.getInt(4);

            Student student = new Student(studentId, stuName, stuLastName, classroom, thisAvg);
            students.add(student);
        }
        tmp.close();

        return students;
    }



    public ArrayList<Student> getStudentsByNameAndClassroom(String name, String classroom){

        ArrayList<Student> students=new ArrayList<>();

        int studentId = 0;
        String stuName = "";
        String stuLastName = "";
        int thisAvg = 0;

        Cursor tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_STUDENT+
                " WHERE "+Utils.TABLE_STUDENT_COL_CLASSNAME+" = '"+classroom+
                "' AND "+Utils.TABLE_STUDENT_COL_NAME+" = '"+name+"'", null);
        while (tmp.moveToNext()){
            studentId = tmp.getInt(0);
            stuName = tmp.getString(1);
            stuLastName = tmp.getString(2);
            thisAvg = tmp.getInt(4);

            Student student = new Student(studentId, stuName, stuLastName, classroom, thisAvg);
            students.add(student);
        }
        tmp.close();

        return students;
    }

    public ArrayList<Student> getStudentsByAvgAndSubject(int avg, String subject){

        ArrayList<Student> students=new ArrayList<>();

        int studentId = 0;
        String studentName = "";
        String studentLastName = "";
        int thisAvg = 0;

        String classroom = "";
        String stuClassroom = "";


        Cursor tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_SUBJECT+
                " WHERE "+Utils.TABLE_SUBJECT_COL_NAME+" = '"+subject+"'", null);
        while (tmp.moveToNext()){
            classroom = tmp.getString(2);
        }
        tmp.close();

        tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_STUDENT+
                " WHERE "+Utils.TABLE_STUDENT_COL_CLASSNAME+" = '"+classroom+"'" +
                " AND "+ Utils.TABLE_STUDENT_COL_AVERAGE +" > " + avg, null);
        while (tmp.moveToNext()){
            studentId = tmp.getInt(0);
            studentName = tmp.getString(1);
            studentLastName = tmp.getString(2);
            thisAvg = tmp.getInt(4);

            Student student = new Student(studentId, studentName, studentLastName, stuClassroom, thisAvg);
            students.add(student);
        }
        tmp.close();

        return students;
    }

    public ArrayList<Student> getStudentsByNameAndSubject(String name, String subject){
        ArrayList<Student> students=new ArrayList<>();

        int studentId = 0;
        String studentLastName = "";
        int thisAvg = 0;

        String classroom = "";
        String stuClassroom = "";

        Cursor tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_SUBJECT+
                " WHERE "+Utils.TABLE_SUBJECT_COL_NAME+" = '"+subject+"'", null);
        while (tmp.moveToNext()){
            classroom = tmp.getString(2);
        }
        tmp.close();

        tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_STUDENT+
                " WHERE "+Utils.TABLE_STUDENT_COL_NAME+" = '"+name+"'", null);
        while (tmp.moveToNext()){
            stuClassroom = tmp.getString(3);
            if(stuClassroom.equals(classroom)){
                studentId = tmp.getInt(0);
                studentLastName = tmp.getString(2);
                thisAvg = tmp.getInt(4);

                Student student = new Student(studentId, name, studentLastName, stuClassroom, thisAvg);
                students.add(student);
            }
        }
        tmp.close();

        return students;
    }
}
