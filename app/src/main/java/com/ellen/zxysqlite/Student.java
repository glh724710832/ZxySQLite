package com.ellen.zxysqlite;

import com.ellen.zxysqlite.table.reflection.Ignore;
import com.ellen.zxysqlite.table.reflection.Primarykey;

public class Student {

    @Primarykey
    private int id;
    private String name;
    private String sex;
    private String phoneNumber;
    private boolean isMan = false;
    private String date = "2018-04-05";
    private Character nameOne = 'b';
    @Ignore
    private Teacher teacher;
    @Ignore
    private String[] projectName = {"语文","数学","英语"};

    public Student(){}

    public Student(int id, String name, String sex, String phoneNumber, boolean isMan,String teacherName) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.isMan = isMan;
        teacher = new Teacher();
        teacher.setName(teacherName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isMan() {
        return isMan;
    }

    public void setMan(boolean man) {
        isMan = man;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Character getNameOne() {
        return nameOne;
    }

    public void setNameOne(Character nameOne) {
        this.nameOne = nameOne;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String[] getProjectName() {
        return projectName;
    }

    public void setProjectName(String[] projectName) {
        this.projectName = projectName;
    }
}
