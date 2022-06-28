package com.example.studentsteachersproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class functionYourSubjectScreen extends AppCompatActivity {

    SQLiteDatabase db;
    Intent intent, intent2;
    Button btn_add_student_subject, btn_search_avrg_student_subject, btn_search_name_student_subject;
    TextView tv_subject_name;
    EditText et_name_new_student_subject, et_lastname_new_student_subject,
            et_avrg_new_student_subject, et_id_new_student_subject, et_avrg_search_student_subject,
            et_name_search_student_subject;

    String subject = "";
    int teacherId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_functions_your_subject);

        db = openOrCreateDatabase(Utils.DATABASE_NAME, MODE_PRIVATE, null);

        btn_add_student_subject = findViewById(R.id.btn_add_student_subject);
        btn_search_avrg_student_subject = findViewById(R.id.btn_search_avrg_student_subject);
        btn_search_name_student_subject = findViewById(R.id.btn_search_name_student_subject);
        tv_subject_name = findViewById(R.id.tv_subject_name);
        et_name_new_student_subject = findViewById(R.id.et_name_new_student_subject);
        et_lastname_new_student_subject = findViewById((R.id.et_lastname_new_student_subject));
        et_avrg_new_student_subject = findViewById(R.id.et_avrg_new_student_subject);
        et_id_new_student_subject = findViewById(R.id.et_id_new_student_subject);
        et_avrg_search_student_subject = findViewById(R.id.et_avrg_search_student_subject);
        et_name_search_student_subject = findViewById(R.id.et_name_search_student_subject);


        intent = getIntent();
        int id = intent.getIntExtra("id", 0);

        int subjectId = 0;

        Cursor tmp = db.rawQuery("select "+Utils.TABLE_TEACHER_COL_SUBJECT_ID+" from "+ Utils.TABLE_NAME_TEACHER+
                " WHERE "+Utils.TABLE_TEACHER_COL_ID+" = '"+id+"'", null);
        while(tmp.moveToNext()) {
            subjectId = tmp.getInt(0);
        }
        tmp.close();



        tmp = db.rawQuery("select "+Utils.TABLE_SUBJECT_COL_NAME+" from "+ Utils.TABLE_NAME_SUBJECT+" WHERE "+Utils.TABLE_SUBJECT_COL_ID+" = '"+subjectId+"'", null);
        while(tmp.moveToNext()) {
            subject = tmp.getString(0);
        }
        tmp.close();

        tv_subject_name.setText(subject);

        btn_add_student_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name_new_student_subject.getText().toString();
                String lName= et_lastname_new_student_subject.getText().toString();
                String avrgString = et_avrg_new_student_subject.getText().toString();
                int avrg = Integer.parseInt(avrgString);
                String idString = et_id_new_student_subject.getText().toString();
                int idStudent= Integer.parseInt(idString);


                intent = getIntent();
                int idTeacher = intent.getIntExtra("id", 0);

                Cursor cur = db.rawQuery("SELECT * FROM "+ Utils.TABLE_NAME_STUDENT+
                        " WHERE "+Utils.TABLE_STUDENT_COL_ID+" = " + idStudent, null);
                if(cur.getCount() >= 1){
                    Toast.makeText(functionYourSubjectScreen.this, "There is already a student with this Id. " +
                            "   Please try again.", Toast.LENGTH_SHORT).show();
                    cur.close();
                }else{

                    int subjectId = 0;

                    Cursor tmp = db.rawQuery("select "+Utils.TABLE_TEACHER_COL_SUBJECT_ID+" from "+ Utils.TABLE_NAME_TEACHER +
                            " WHERE "+Utils.TABLE_TEACHER_COL_ID+" = '"+ idTeacher +"'", null);
                    while(tmp.moveToNext()){
                        subjectId = tmp.getInt(0);
                    }
                    tmp.close();

                    String classname ="";

                    tmp = db.rawQuery("SELECT "+Utils.TABLE_SUBJECT_COL_CLASSROOM+" FROM "+Utils.TABLE_NAME_SUBJECT+
                            " WHERE "+Utils.TABLE_SUBJECT_COL_ID+" = "+ subjectId, null);
                    while(tmp.moveToNext()){
                        classname = tmp.getString(0);
                    }
                    tmp.close();

                    db.execSQL("insert into "+Utils.TABLE_NAME_STUDENT+" values(" + idStudent + ", '"+name+"','"+lName+"','" + classname + "'," + avrg + ")");


                    intent2 = new Intent(functionYourSubjectScreen.this, teacherScreen.class);
                    intent2.putExtra("id", id);
                    startActivity(intent2);
                    cur.close();
                }
            }
        });

        btn_search_avrg_student_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String avgString = et_avrg_search_student_subject.getText().toString();
                int avg = Integer.parseInt(avgString);

                intent = new Intent(functionYourSubjectScreen.this, searchByScreen.class);
                intent.putExtra("avg", avg);
                intent.putExtra("subject", subject);
                intent.putExtra("name", "");
                intent.putExtra("classroom", "");

                startActivity(intent);
            }
        });

        btn_search_name_student_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = et_name_search_student_subject.getText().toString();

                intent = new Intent(functionYourSubjectScreen.this, searchByScreen.class);
                intent.putExtra("name", name);
                intent.putExtra("subject", subject);
                intent.putExtra("avg", -1);
                intent.putExtra("classroom", "");

                startActivity(intent);
            }
        });


    }

    }
