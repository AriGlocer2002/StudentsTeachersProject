package com.example.studentsteachersproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    SQLiteDatabase db;
    int click=0;
    Cursor tmp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        db = openOrCreateDatabase(Utils.DATABASE_NAME,
                MODE_PRIVATE, null);

        Utils.createTables(db);
        Utils.addALLToDb(db);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.opt_teacher:
                startActivity(new Intent(SplashScreen.this, teacherCheckScreen.class));
                return true;
            case R.id.opt_student:
                startActivity(new Intent(SplashScreen.this, studentCheckScreen.class));
                return true;
            case R.id.opt_allStudents:
                startActivity(new Intent(SplashScreen.this, allStudentsScreen.class));
                return true;
        }
        return true;
    }
}