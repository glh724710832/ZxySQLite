package com.ellen.zxysqlite;

import android.database.sqlite.SQLiteDatabase;

import com.ellen.zxysqlite.createsql.add.AddManyRowToTable;
import com.ellen.zxysqlite.createsql.create.createtable.Field;
import com.ellen.zxysqlite.createsql.helper.Value;
import com.ellen.zxysqlite.table.ZxySingleTable;

import java.util.ArrayList;
import java.util.List;

public class Student extends ZxySingleTable<Student> {

    private int id;
    private String name;
    private String sex;
    private String phoneNumber;

    public Student(SQLiteDatabase db) {
        super(db);
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

    @Override
    protected String getTableName() {
        return this.getClass().getSimpleName();
    }

    @Override
    protected List<Field> getCreateSQLFields() {
        List<Field> fieldList = new ArrayList<>();
        fieldList.add(Field.getPrimaryKeyField("id","int",false));
        fieldList.add(Field.getNotNullValueField("name","text"));
        fieldList.add(Field.getNotNullValueField("sex","text"));
        fieldList.add(Field.getNotNullValueField("phone_number","text"));
        return fieldList;
    }

    @Override
    protected boolean addData(Student data) {
        String addSingleDataSQL = getAddSingleRowToTable()
                .setTableName(getTableName())
                .addData(new Value("id",data.getId()))
                .addData(new Value("name",data.getName()))
                .addData(new Value("sex",data.getSex()))
                .addData(new Value("phone_number",data.getPhoneNumber()))
                .createSQL();
        getSQLiteDatabase().execSQL(addSingleDataSQL);
        return true;
    }

    @Override
    protected boolean addData(List<Student> datas) {
        AddManyRowToTable addManyRowToTable = AddManyRowToTable.getInstance()
                .addFieldList(getCreateSQLFields());
        for(Student student:datas){
            List list = new ArrayList();
            list.add(student.getId());
            list.add(student.getName());
            list.add(student.getSex());
            list.add(student.getPhoneNumber());
            addManyRowToTable.addValueList(list);
        }
        getSQLiteDatabase().execSQL(addManyRowToTable.createSQL());
        return true;
    }

    @Override
    protected boolean deleteData(String deleteSQL) {
        getSQLiteDatabase().execSQL(deleteSQL);
        return true;
    }

    @Override
    protected boolean updateData(String whereSQL, Student student) {
        return false;
    }

    @Override
    protected List<Student> serachData(String whereSQL) {
        return null;
    }
}
