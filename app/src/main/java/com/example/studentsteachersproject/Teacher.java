package com.example.studentsteachersproject;

public class Teacher {
    private String name;
    private String lastName;
    private int subjectId;
    private int id;

    public Teacher(int id, String name, String lastName, int subjectId) {
        this.name = name;
        this.lastName = lastName;
        this.subjectId = subjectId;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", id=" + id +
                '}';
    }
}
