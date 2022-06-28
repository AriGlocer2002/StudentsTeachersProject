package com.example.studentsteachersproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class teacherScreen extends AppCompatActivity {

    Button btn_your_subject, btn_your_classroom;
    SQLiteDatabase db;
    Intent intent;
    TextView tv_your_subject, tv_your_classroom;

    ListView lv_my_classroom, lv_my_subject_class;
    studentAdapter adapter_classroom, adapter_subject;
    ArrayList<Student> students_classroom, students_subject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);

        btn_your_classroom=findViewById(R.id.btn_your_classroom);
        btn_your_subject=findViewById(R.id.btn_your_subject);
        tv_your_classroom=findViewById(R.id.tv_your_classroom);
        tv_your_subject=findViewById(R.id.tv_your_subject);
        lv_my_classroom = findViewById(R.id.lv_my_classroom);
        lv_my_subject_class = findViewById(R.id.lv_my_subject_class);


        intent = getIntent();
        int id = intent.getIntExtra("id", 0);
        String classroom="";

        Cursor tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_CLASSROOM, null);
        while(tmp.moveToNext()) {
            int tutorId = tmp.getInt(1);
            if (id == tutorId) {
              classroom = tmp.getString(0);
            }
        }
        tmp.close();

        if(!classroom.equals("")) {
            tv_your_classroom.setText(tv_your_classroom.getText() + " " + classroom);
        } else{
            tv_your_classroom.setText(tv_your_classroom.getText()+" no classroom");
        }


        int subjectId=0;


        tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_TEACHER, null);
        while(tmp.moveToNext()) {
            int tutorId = tmp.getInt(0);
            if (id == tutorId) {
                subjectId = tmp.getInt(3);
            }
        }
        tmp.close();

        String subject="";

        tmp = db.rawQuery("select name from "+ Utils.TABLE_NAME_SUBJECT+" WHERE id = '"+subjectId+"'", null);
        while(tmp.moveToNext()) {
                subject = tmp.getString(0);
        }
        tmp.close();

        tv_your_subject.setText(tv_your_subject.getText()+" "+subject);

        String finalClassroom = classroom;
        btn_your_classroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finalClassroom.equals("")) {
                    intent = new Intent(teacherScreen.this, functionYourClassroomScreen.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else{
                    Toast.makeText(teacherScreen.this, "You are not a tutor of any class", Toast.LENGTH_SHORT).show();
                }

            }
        });

        String finalSubject = subject;
        btn_your_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finalSubject.equals("")) {
                    intent = new Intent(teacherScreen.this, functionYourSubjectScreen.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }else{
                    Toast.makeText(teacherScreen.this, "You are not currently teacher of any subject", Toast.LENGTH_SHORT).show();
                }
            }
        });


        students_classroom = getStudentsSameClassroom(id);
        adapter_classroom = new studentAdapter(students_classroom, teacherScreen.this);
        lv_my_classroom.setAdapter(adapter_classroom);

        lv_my_classroom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Student student = students_classroom.get(position);
                Intent intent2 = new Intent(teacherScreen.this, studentEditAndDeleteScreen.class);
                intent2.putExtra("id", id);
                intent2.putExtra(Utils.INTENT_KEY_STUDENT_ID, student.getId());
                intent2.putExtra(Utils.INTENT_KEY_STUDENT_NAME, student.getName());
                intent2.putExtra(Utils.INTENT_KEY_STUDENT_LASTNAME, student.getLastName());
                intent2.putExtra(Utils.INTENT_KEY_STUDENT_CLASSNAME, student.getClassName());
                intent2.putExtra(Utils.INTENT_KEY_STUDENT_AVERAGE, student.getAverage());

                startActivity(intent2);
            }
        });

        students_subject = getStudentsSameSubject(id);
        adapter_subject = new studentAdapter(students_subject, teacherScreen.this);
        lv_my_subject_class.setAdapter(adapter_subject);

        lv_my_subject_class.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                Student student2 = students_subject.get(position);
                Intent intent3 = new Intent(teacherScreen.this, studentEditAndDeleteScreen.class);
                intent3.putExtra("id", id);
                intent3.putExtra(Utils.INTENT_KEY_STUDENT_ID, student2.getId());
                intent3.putExtra(Utils.INTENT_KEY_STUDENT_NAME, student2.getName());
                intent3.putExtra(Utils.INTENT_KEY_STUDENT_LASTNAME, student2.getLastName());
                intent3.putExtra(Utils.INTENT_KEY_STUDENT_CLASSNAME, student2.getClassName());
                intent3.putExtra(Utils.INTENT_KEY_STUDENT_AVERAGE, student2.getAverage());

                startActivity(intent3);
            }
        });
}

    public ArrayList<Student> getStudentsSameClassroom(int id) {

        ArrayList<Student> students = new ArrayList<>();

        int tutorId = 0;
        String className = "";

        Cursor tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_CLASSROOM + " where " + Utils.TABLE_CLASSROOM_COL_TUTORID + " = " + id, null);
        while(tmp.moveToNext()) {
            className = tmp.getString(0);
        }
        tmp.close();


        String studentClassName;
        tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_STUDENT + " where "+Utils.TABLE_STUDENT_COL_CLASSNAME+" = '" + className + "'", null);
        while(tmp.moveToNext()) {
            int idStudent = tmp.getInt(0);
            String name = tmp.getString(1);
            String lname = tmp.getString(2);
            int average = tmp.getInt(4);

            Student student = new Student(idStudent, name, lname, className, average);

            students.add(student);
        }
        tmp.close();

        return students;
    }


    public ArrayList<Student> getStudentsSameSubject(int id){

        ArrayList<Student> students = new  ArrayList<>();

        int subjectId = 0;
        String classroomName = "";

        Cursor tmp = db.rawQuery("select "+Utils.TABLE_TEACHER_COL_SUBJECT_ID+" from "+ Utils.TABLE_NAME_TEACHER+
                " WHERE "+Utils.TABLE_TEACHER_COL_ID+" = "+id, null);
        while(tmp.moveToNext()) {
            subjectId = tmp.getInt(0);
        }
        tmp.close();



        tmp = db.rawQuery("select "+Utils.TABLE_SUBJECT_COL_CLASSROOM+" from "+ Utils.TABLE_NAME_SUBJECT+
                " WHERE "+Utils.TABLE_SUBJECT_COL_ID+" = " + subjectId, null);
        while(tmp.moveToNext()) {
            classroomName = tmp.getString(0);
        }
        tmp.close();


        String name;
        String lname;
        int studentId;
        int average;

        tmp = db.rawQuery("select * from "+ Utils.TABLE_NAME_STUDENT+
                " WHERE "+Utils.TABLE_STUDENT_COL_CLASSNAME+" = '"+classroomName+"'", null);
        while(tmp.moveToNext()) {
            name = tmp.getString(1);
            lname = tmp.getString(2);
            studentId = tmp.getInt(0);
            average = tmp.getInt(4);

            Student student = new Student(studentId, name, lname, classroomName, average);
            students.add(student);
        }
        tmp.close();

        return students;
    }

}
