package com.example.studentsteachersproject;

public class Classroom {

    private String className;
    private int tutorId;

    public Classroom(String className, int tutorId) {
        this.className = className;
        this.tutorId = tutorId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getTutorId() {
        return tutorId;
    }

    public void setTutorId(int tutorId) {
        this.tutorId = tutorId;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "className='" + className + '\'' +
                ", tutorName='" + tutorId + '\'' +
                '}';
    }
}
