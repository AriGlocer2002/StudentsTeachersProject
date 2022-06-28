package com.example.studentsteachersproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class studentEditAndDeleteScreen extends AppCompatActivity {


    SQLiteDatabase db;
    Button btn_delete, btn_save_edit_student;
    EditText et_name_edit, et_last_name_edit, et_id_edit, et_classroom_edit, et_average_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_edit_and_delete);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);

        btn_save_edit_student = findViewById(R.id.btn_save_edit_student);
        btn_delete = findViewById(R.id.btn_delete);
        et_name_edit = findViewById(R.id.et_name_edit);
        et_last_name_edit = findViewById(R.id.et_last_name_edit);
        et_id_edit = findViewById(R.id.et_id_edit);
        et_classroom_edit = findViewById(R.id.et_classroom_edit);
        et_average_edit = findViewById(R.id.et_average_edit);

        Intent intent = getIntent();
        int tutorId = intent.getIntExtra("id", 0);

        int old_id = intent.getIntExtra(Utils.INTENT_KEY_STUDENT_ID, 0);
        et_id_edit.setText("" + intent.getIntExtra(Utils.INTENT_KEY_STUDENT_ID, 0));
        et_name_edit.setText(intent.getStringExtra(Utils.INTENT_KEY_STUDENT_NAME));
        et_last_name_edit.setText(intent.getStringExtra(Utils.INTENT_KEY_STUDENT_LASTNAME));
        et_classroom_edit.setText(intent.getStringExtra(Utils.INTENT_KEY_STUDENT_CLASSNAME));
        et_average_edit.setText("" + intent.getIntExtra(Utils.INTENT_KEY_STUDENT_AVERAGE, 0));

        btn_save_edit_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = Integer.parseInt(et_id_edit.getText().toString());
                String name = et_name_edit.getText().toString();
                String lname = et_last_name_edit.getText().toString();
                String className = et_classroom_edit.getText().toString();
                int average = Integer.parseInt(et_average_edit.getText().toString());

                Student student = new Student(id, name, lname, className, average);

                if(!Utils.checkId(old_id, id, db)){
                    Utils.updateStudent(old_id, student, db);

                    Toast.makeText(studentEditAndDeleteScreen.this, "Information about this student was successfully updated", Toast.LENGTH_SHORT).show();

                    Intent intent2 = new Intent(studentEditAndDeleteScreen.this, teacherScreen.class);
                    intent2.putExtra("id", tutorId);
                    startActivity(intent2);
                }
                else {
                    Toast.makeText(studentEditAndDeleteScreen.this, "Try another id", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Utils.deleteStudent(old_id, db);

                Intent intent3 = new Intent(studentEditAndDeleteScreen.this, teacherScreen.class);
                intent3.putExtra("id", tutorId);
                startActivity(intent3);
            }
        });


    }
}
