package com.example.studentsteachersproject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class Utils {

    final static String INTENT_KEY_STUDENT_NAME = "student_name";
    final static String INTENT_KEY_STUDENT_LASTNAME = "student_lastname";
    final static String INTENT_KEY_STUDENT_CLASSNAME = "student_classname";
    final static String INTENT_KEY_STUDENT_AVERAGE = "student_average";
    final static String INTENT_KEY_STUDENT_ID = "student_id";

    final static String INTENT_KEY_TEACHER_NAME = "teacher_name";
    final static String INTENT_KEY_TEACHER_LASTNAME = "teacher_lastname";
    final static String INTENT_KEY_TEACHER_SUBJECT_ID = "teacher_subject_id";
    final static String INTENT_KEY_TEACHER_ID = "teacher_id";

    final static String INTENT_KEY_CLASSROOM_NAME = "classroom_name";
    final static String INTENT_KEY_CLASSROOM_TUTOR_ID = "classroom_tutor_id";

    final static String INTENT_KEY_SUBJECT_NAME = "subject_name";
    final static String INTENT_KEY_SUBJECT_ID = "subject_id";
    final static String INTENT_KEY_SUBJECT_CLASSROOM = "subject_classroom";

    final static String DATABASE_NAME = "db_school_app";

    final static String TABLE_NAME_STUDENT = "tbl_student";
    final static String TABLE_STUDENT_COL_NAME = "name";
    final static String TABLE_STUDENT_COL_LASTNAME = "lastname";
    final static String TABLE_STUDENT_COL_CLASSNAME = "classname";
    final static String TABLE_STUDENT_COL_AVERAGE = "average";
    final static String TABLE_STUDENT_COL_ID = "id";

    final static String TABLE_NAME_TEACHER="tbl_teacher";
    final static String TABLE_TEACHER_COL_NAME = "name";
    final static String TABLE_TEACHER_COL_LASTNAME = "lastname";
    final static String TABLE_TEACHER_COL_SUBJECT_ID = "subject_id";
    final static String TABLE_TEACHER_COL_ID = "id";

    final static String TABLE_NAME_CLASSROOM="tbl_classroom";
    final static String TABLE_CLASSROOM_COL_NAME = "name";
    final static String TABLE_CLASSROOM_COL_TUTORID = "tutorid";

    final static String TABLE_NAME_SUBJECT = "tbl_subject";
    final static String TABLE_SUBJECT_COL_NAME = "name";
    final static String TABLE_SUBJECT_COL_ID = "id";
    final static String TABLE_SUBJECT_COL_CLASSROOM = "classroom";

    public static void createTables(SQLiteDatabase db) {
        db.execSQL("drop table if exists "+ Utils.TABLE_NAME_STUDENT);
        db.execSQL("create table if not exists "+Utils.TABLE_NAME_STUDENT+
                " ("+Utils.TABLE_STUDENT_COL_ID+" integer , "+
                Utils.TABLE_STUDENT_COL_NAME+" text, "+
                Utils.TABLE_STUDENT_COL_LASTNAME+" text, "+
                Utils.TABLE_STUDENT_COL_CLASSNAME+" text, "+
                Utils.TABLE_STUDENT_COL_AVERAGE+" float)");

        db.execSQL("drop table if exists "+ Utils.TABLE_NAME_TEACHER);
        db.execSQL("create table if not exists "+Utils.TABLE_NAME_TEACHER+
                " ("+Utils.TABLE_TEACHER_COL_ID+" integer primary key, "+
                Utils.TABLE_TEACHER_COL_NAME+" text, " +
                Utils.TABLE_TEACHER_COL_LASTNAME+" text, "+
                Utils.TABLE_TEACHER_COL_SUBJECT_ID+" integer)");

        db.execSQL("drop table if exists "+ Utils.TABLE_NAME_CLASSROOM);
        db.execSQL("create table if not exists "+Utils.TABLE_NAME_CLASSROOM+
                " ("+Utils.TABLE_CLASSROOM_COL_NAME+" text primary key, " +
                Utils.TABLE_CLASSROOM_COL_TUTORID+" integer)");

        db.execSQL("drop table if exists "+ Utils.TABLE_NAME_SUBJECT);
        db.execSQL("create table if not exists "+Utils.TABLE_NAME_SUBJECT+
                " ("+Utils.TABLE_SUBJECT_COL_ID+" integer primary key, " +
                Utils.TABLE_SUBJECT_COL_NAME+" text, "+
                Utils.TABLE_SUBJECT_COL_CLASSROOM+" text)");
    }

    public static void addALLToDb(SQLiteDatabase db){
        db.execSQL("delete from "+Utils.TABLE_NAME_STUDENT);
        Student s1 = new Student(23344, "Ariel","Glocer", "4thA", 9);
        Student s2 = new Student(23455, "Peter", "Pan", "5thC", 5);
        Student s3 = new Student(23460, "Mark", "Zuckerberg", "5thC", 10);
        Student s4 = new Student(23498, "Sasha", "Puchov", "2ndD", 3);
        Student s5 = new Student(23500, "Sasha", "Naumov", "2ndD", 10);
        Student s6 = new Student(66589, "Daniel", "Hyman", "3rdE", 9);
        Student s7 = new Student(67380, "Itai", "Maman", "2ndD", 4);
        Student s8 = new Student(89740, "Daniel", "Bykov", "3rdE", 2);
        Student s9 = new Student(78634, "Roman", "Kangaroo", "2ndD", 10);
        Student s10 = new Student(53462, "Murad", "Rahimli", "3rdE", 10);
        Student s11 = new Student(46478, "Amos", "Shubeli", "3rdE", 9);
        Student s12 = new Student(37574, "Vladimir", "Putin", "5thC", 20);
        Student s13 = new Student(89685, "Donald", "Trump", "4thA", 7);
        Student s14 = new Student(34125, "Dominicano", "Toronto", "4thA", 1);
        Student s15 = new Student(35436, "Bill", "Gates", "1stB", 10);
        Student s16 = new Student(87876, "El", "Padrino", "5thC", 3);
        Student s17 = new Student(25675, "Garfield", "Lasagna", "5thC", 4);
        Student s18 = new Student(98765, "Axl", "Rose", "1stB", 9);
        Student s19 = new Student(74746, "James", "Bond", "2ndD", 5);
        Student s20 = new Student(95675, "Diego", "Maradona", "5thC", 10);



        ArrayList<Student> studentList = new ArrayList<>();
        studentList.add(s1);
        studentList.add(s2);
        studentList.add(s3);
        studentList.add(s4);
        studentList.add(s5);
        studentList.add(s6);
        studentList.add(s7);
        studentList.add(s8);
        studentList.add(s9);
        studentList.add(s10);
        studentList.add(s11);
        studentList.add(s12);
        studentList.add(s13);
        studentList.add(s14);
        studentList.add(s15);
        studentList.add(s16);
        studentList.add(s17);
        studentList.add(s18);
        studentList.add(s19);
        studentList.add(s20);

        for (Student s: studentList) {
            db.execSQL("insert into "+ Utils.TABLE_NAME_STUDENT +
                    " values('"+s.getId()+ "',"
                    +"'"+s.getName()+"',"
                    +"'"+s.getLastName()+"',"
                    +"'"+s.getClassName()+"',"
                    +"'"+s.getAverage()+"')");
        }

        db.execSQL("delete from "+Utils.TABLE_NAME_TEACHER);
        Teacher t1 = new Teacher(1001, "Brian","Smith", 10);
        Teacher t2 = new Teacher(1002, "Bob","Dylan", 50);
        Teacher t3 = new Teacher(1003, "Juan","Perez", 20);
        Teacher t4 = new Teacher(1004, "Luigi","Bosca", 30);

        ArrayList<Teacher> teacherList = new ArrayList<>();
        teacherList.add(t1);
        teacherList.add(t2);
        teacherList.add(t3);
        teacherList.add(t4);

        for (Teacher t: teacherList) {
            db.execSQL("insert into "+Utils.TABLE_NAME_TEACHER +
                    " values('"+t.getId()+"',"
                    +"'"+t.getName()+"',"
                    +"'"+t.getLastName()+"',"
                    +"'"+t.getSubjectId()+"')");
        }

        db.execSQL("delete from "+Utils.TABLE_NAME_CLASSROOM);
        Classroom c1 = new Classroom("4thA",1001);
        Classroom c2 = new Classroom("2ndD",1002);
        Classroom c3 = new Classroom("5thC",1003);

        ArrayList<Classroom> clasroomList = new ArrayList<>();
        clasroomList.add(c1);
        clasroomList.add(c2);
        clasroomList.add(c3);

        for (Classroom c: clasroomList) {
            db.execSQL("insert into "+Utils.TABLE_NAME_CLASSROOM +
                    " values('"+c.getClassName()+"',"
                    +"'"+c.getTutorId()+"')");
        }

        db.execSQL("delete from "+Utils.TABLE_NAME_SUBJECT);
        Subject sb1 = new Subject(10, "MATH", "4thA");
        Subject sb2 = new Subject(20, "GEOGRAPHY", "2ndD");
        Subject sb3 = new Subject(30, "PHYSICS", "2ndD");
        Subject sb4 = new Subject(40, "PROGRAMMING I", "3rdE");
        Subject sb5 = new Subject(50, "MUSIC", "4thB");


        ArrayList<Subject> subjectList = new ArrayList<>();
        subjectList.add(sb1);
        subjectList.add(sb2);
        subjectList.add(sb3);
        subjectList.add(sb4);
        subjectList.add(sb5);

        for (Subject s: subjectList) {
            db.execSQL("insert into "+Utils.TABLE_NAME_SUBJECT +
                    " values('"+s.getId()+"',"
                    +"'"+s.getName()+"',"
                    +"'"+s.getClassName()+"')");
        }
    }

    public static  void updateStudent(int old_id, Student student, SQLiteDatabase db){
        if(old_id == student.getId()){
            db.execSQL("update " + Utils.TABLE_NAME_STUDENT+" set "+Utils.TABLE_STUDENT_COL_NAME+" = '"+student.getName()+
                    "' where "+Utils.TABLE_STUDENT_COL_ID+" = " + student.getId());
            db.execSQL("update " + Utils.TABLE_NAME_STUDENT+" set "+Utils.TABLE_STUDENT_COL_LASTNAME+" = '"+student.getLastName()+
                    "' where "+Utils.TABLE_STUDENT_COL_ID+" = " + student.getId());
            db.execSQL("update " + Utils.TABLE_NAME_STUDENT+" set "+Utils.TABLE_STUDENT_COL_CLASSNAME+" = '"+student.getClassName()+
                    "' where "+Utils.TABLE_STUDENT_COL_ID+" = " + student.getId());
            db.execSQL("update " + Utils.TABLE_NAME_STUDENT+" set "+Utils.TABLE_STUDENT_COL_AVERAGE+" = "+student.getAverage()+
                    " where "+Utils.TABLE_STUDENT_COL_ID+" = " + student.getId());
        }
        else {
            db.delete(Utils.TABLE_NAME_STUDENT, Utils.TABLE_STUDENT_COL_ID + " = " + old_id, null);
            db.execSQL("insert into " + Utils.TABLE_NAME_STUDENT +
                    " values( "+student.getId()+", '"+student.getName()+"', '"+student.getLastName()+"','"+student.getClassName()+"', "+student.getAverage()+")");
        }
    }

    public static boolean checkId(int old_id, int new_id, SQLiteDatabase db){
        if(old_id == new_id){
            return false;
        }
        Cursor cursor = db.rawQuery("select "+Utils.TABLE_STUDENT_COL_ID+" from "+Utils.TABLE_NAME_STUDENT, null);
        while (cursor.moveToNext()){
            if(new_id == cursor.getInt(0)){
                return true;
            }
        }

        return false;
    }

    public static void deleteStudent(int id, SQLiteDatabase db){
        db.delete(Utils.TABLE_NAME_STUDENT, Utils.TABLE_STUDENT_COL_ID + " = " + id, null);
        Log.d("check", "id of student" + id);

    }
}