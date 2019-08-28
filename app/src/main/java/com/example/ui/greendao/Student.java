package com.example.ui.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Student {
    @Id(autoincrement = true)
    Long id;

    public int getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(int studentNo) {
        this.studentNo = studentNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Unique
    int studentNo;
    int age;
    String name;

    @Generated(hash = 89133420)
    public Student(Long id, int studentNo, int age, String name) {
        this.id = id;
        this.studentNo = studentNo;
        this.age = age;
        this.name = name;
    }

    @Generated(hash = 1556870573)
    public Student() {
    }


}