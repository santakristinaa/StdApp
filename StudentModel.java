package com.example.midtest;

public class StudentModel {
    private Integer id;
    private String name;
    private String studentId;
    private int age;

    public StudentModel(String name, String studentId, int age) {
        this.name = name;
        this.studentId = studentId;
        this.age = age;
    }

    public StudentModel(Integer id, String name, String studentId, int age) {
        this.id = id;
        this.name = name;
        this.studentId = studentId;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}