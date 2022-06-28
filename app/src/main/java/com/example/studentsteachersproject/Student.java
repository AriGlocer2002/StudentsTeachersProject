package com.example.studentsteachersproject;

public class Student {
    private String name;
    private String lastName;
    private String className;
    private int average;
    private int id;

    public Student(int id, String n, String lastN, String className, int avrg){
        this.name=n;
        this.lastName=lastN;
        this.className=className;
        this.average=avrg;
        this.id=id;
    }

    public Student() {
        this.name="";
        this.lastName="";
        this.id=0;
        this.className="";
        this.average=0;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getClassName() {
        return className;
    }

    public int getAverage() {
        return average;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setAverage(int average) {
        this.average = average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", className='" + className + '\'' +
                ", average=" + average +
                ", id=" + id +
                '}';
    }
}
