package com.example.studentsteachersproject;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class studentCheckScreen extends AppCompatActivity {


    SQLiteDatabase db;
    Button btn_student_login;
    Intent intent;
    EditText et_student_name, et_student_last_name, et_student_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_check_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);

        btn_student_login=findViewById(R.id.btn_login_student);
        et_student_name=findViewById(R.id.et_student_name);
        et_student_last_name=findViewById(R.id.et_student_last_name);
        et_student_id=findViewById(R.id.et_student_id);




        btn_student_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=et_student_name.getText().toString();
                String lName= et_student_last_name.getText().toString();
                String id= et_student_id.getText().toString();
                int finalValue_id=Integer.parseInt(id);

                Cursor c = db.rawQuery("SELECT * FROM "+ Utils.TABLE_NAME_STUDENT+
                        " WHERE "+Utils.TABLE_STUDENT_COL_NAME+" = '"+name+"' AND " +
                        Utils.TABLE_STUDENT_COL_LASTNAME+" = '" + lName + "' AND " +
                        Utils.TABLE_STUDENT_COL_ID+" = '" + id + "'", null);


                if(c.getCount() == 1){
                    c.close();
                    intent = new Intent (studentCheckScreen.this, studentScreen.class);
                    intent.putExtra("id", finalValue_id);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(studentCheckScreen.this, "Wrong data. Try again.", Toast.LENGTH_SHORT).show();
                }
                c.close();

            }
        });

    }
}
