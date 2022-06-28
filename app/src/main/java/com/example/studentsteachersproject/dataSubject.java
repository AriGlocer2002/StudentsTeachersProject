package com.example.studentsteachersproject;

public class dataSubject {

    private String subjectName;
    private  String teacherName;
    private  String teacherLastName;

    public dataSubject(String subjectName, String teacherName, String teacherLastName) {
        this.subjectName = subjectName;
        this.teacherName = teacherName;
        this.teacherLastName = teacherLastName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    @Override
    public String toString() {
        return "dataSubject{" +
                "subjectName='" + subjectName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", teacherLastName='" + teacherLastName + '\'' +
                '}';
    }
}
