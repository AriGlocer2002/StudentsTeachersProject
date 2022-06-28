package com.example.studentsteachersproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class studentScreen extends AppCompatActivity {

    SQLiteDatabase db;
    Intent intent;
    TextView tv_name_student, tv_last_name_student;
    dataSubjectAdapter adapter;
    ListView lv_subjects;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);

        tv_name_student=findViewById(R.id.tv_name_student);
        tv_last_name_student=findViewById(R.id.tv_last_name_student);
        lv_subjects=findViewById(R.id.lv_subjects);

        intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        String name="";
        String lname="";

        Cursor tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_STUDENT+
                " WHERE "+Utils.TABLE_STUDENT_COL_ID+" = "+id, null);
        while(tmp.moveToNext()) {
                name = tmp.getString(1);
                lname = tmp.getString(2);
        }
        tmp.close();

        tv_name_student.setText(tv_name_student.getText()+" "+name);
        tv_last_name_student.setText(tv_last_name_student.getText()+" "+lname);


        ArrayList<dataSubject> dataSubjects = getDataSubjectList(id);
        adapter = new dataSubjectAdapter(dataSubjects, studentScreen.this);
        lv_subjects.setAdapter(adapter);
    }


    public ArrayList<dataSubject> getDataSubjectList(int studentId){

        ArrayList<dataSubject> dataSubjects = new  ArrayList<>();

        String classroom = "";

        Cursor tmp = db.rawQuery("select "+Utils.TABLE_STUDENT_COL_CLASSNAME+" from "+ Utils.TABLE_NAME_STUDENT+
                " WHERE "+Utils.TABLE_STUDENT_COL_ID+" = '"+studentId+"'", null);
        while(tmp.moveToNext()) {
           classroom = tmp.getString(0);
        }
        tmp.close();


        int subjectId = 0;

        tmp = db.rawQuery("select "+Utils.TABLE_SUBJECT_COL_ID+" from "+ Utils.TABLE_NAME_SUBJECT+
                " WHERE "+Utils.TABLE_SUBJECT_COL_CLASSROOM+" = '"+classroom+"'", null);
        while(tmp.moveToNext()) {
            subjectId = tmp.getInt(0);
        }
        tmp.close();

        String teacherName = "";
        String teacherLastName = "";
        String subjectName = "";


        //private final String MY_QUERY = "SELECT * FROM table_a a INNER JOIN table_b b ON a.id=b.other_id WHERE b.property_id=?";


        tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_SUBJECT+" S INNER JOIN "+Utils.TABLE_NAME_TEACHER+" T ON S.id = T.subject_id"+
                " WHERE T.subject_id = "+ subjectId, null);
        while (tmp.moveToNext()){
            subjectName = tmp.getString(1);
            teacherName = tmp.getString(4);
            teacherLastName = tmp.getString(5);


            dataSubject dSub = new dataSubject(subjectName, teacherName, teacherLastName);
            dataSubjects.add(dSub);
        }

        return dataSubjects;
    }
}
