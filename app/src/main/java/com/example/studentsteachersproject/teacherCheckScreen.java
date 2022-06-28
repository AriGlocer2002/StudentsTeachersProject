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

public class teacherCheckScreen extends AppCompatActivity {

    SQLiteDatabase db;
    Button btn_teacher_login;
    Intent intent;
    EditText et_teacher_name, et_teacher_last_name, et_teacher_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_check_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);

        btn_teacher_login=findViewById(R.id.btn_login_teacher);
        et_teacher_name=findViewById(R.id.et_teacher_name);
        et_teacher_last_name=findViewById(R.id.et_teacher_last_name);
        et_teacher_id=findViewById(R.id.et_teacher_id);

        btn_teacher_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=et_teacher_name.getText().toString();
                String lName= et_teacher_last_name.getText().toString();
                String id= et_teacher_id.getText().toString();
                int finalValue_id=Integer.parseInt(id);

                Cursor c = db.rawQuery("SELECT * FROM "+ Utils.TABLE_NAME_TEACHER+
                        " WHERE "+Utils.TABLE_TEACHER_COL_NAME+" = '"+name+"' AND " +
                        Utils.TABLE_TEACHER_COL_LASTNAME+" = '" + lName + "' AND " +
                        Utils.TABLE_TEACHER_COL_ID+" = '" + id + "'", null);


                    if(c.getCount() == 1){
                        c.close();
                        intent = new Intent (teacherCheckScreen.this, teacherScreen.class);
                        intent.putExtra("id", finalValue_id);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(teacherCheckScreen.this, "Wrong data. Try again.", Toast.LENGTH_SHORT).show();
                    }
                    c.close();

            }
        });
    }
}
