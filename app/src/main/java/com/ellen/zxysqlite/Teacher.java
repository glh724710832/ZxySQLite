package com.ellen.zxysqlite;

public class Teacher {

    private int id;
    private String name;
    private String sex;
    private String phoneNumber;
    private boolean isMan = false;

    public Teacher(int id, String name, String sex, String phoneNumber, boolean isMan) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.isMan = isMan;
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
}
