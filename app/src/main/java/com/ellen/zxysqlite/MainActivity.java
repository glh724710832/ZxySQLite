package com.ellen.zxysqlite;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.ellen.zxysqlite.createsql.create.createtable.CreateTable;
import com.ellen.zxysqlite.createsql.create.createtable.SQLField;
import com.ellen.zxysqlite.createsql.helper.WhereSymbolEnum;
import com.ellen.zxysqlite.createsql.order.Order;
import com.ellen.zxysqlite.createsql.update.UpdateTableDataRow;
import com.ellen.zxysqlite.createsql.where.Where;
import com.ellen.zxysqlite.createsql.where.WhereIn;
import com.ellen.zxysqlite.singletable.StudentReflectionTable;
import com.ellen.zxysqlite.table.reflection.ZxyReflectionTable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        String whereSql = Where.getInstance(false)
//                .addAndWhereValue("id",WhereSymbolEnum.LESS_THAN,4)
//                .addAndWhereValue("name",WhereSymbolEnum.LIKE,"ss")
//                .createSQL();
//        String orderSql = Order.getInstance(false)
//                .setFirstOrderFieldName("id")
//                .setSecondOrderFieldName("name")
//                .setIsDesc(false)
//                .createSQL();
//        String sql = SerachTableData.getInstance()
//                .setTableName("student")
//                .addSelectField("id")
//                .addSelectField("name")
//                .createSQLAutoWhere(whereSql,orderSql);
        //创建表的SQL语句
        String sql = CreateTable.getInstance()
                .setTableName("student")
                .addField(SQLField.getPrimaryKeyField("id","int",true))
                .addField(SQLField.getNotNullContainsDefaultValueField("name","text","阿三"))
                .addField(SQLField.getOrdinaryField("sex","text"))
                .createSQLIfNotExists();
        //textView.setText(sql);
        initView();

    }


    private void initView(){
        textView = findViewById(R.id.tv);
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(this,"library",null,1);
        final StudentReflectionTable zxyReflectionTable = new StudentReflectionTable(mySQLiteHelper.getReadableDatabase(),Student.class,"my_student");
        zxyReflectionTable.onCreateTable(new ZxyReflectionTable.OnCreateSQLiteCallback() {
            @Override
            public void onCreateTableBefore(String tableName, List<SQLField> sqlFieldList, String createSQL) {

            }

            @Override
            public void onCreateTableFailure(String errMessage, String tableName, List<SQLField> sqlFieldList, String createSQL) {
                Log.e("Ellen失败",tableName+":"+createSQL);
            }

            @Override
            public void onCreateTableSuccess(String tableName, List<SQLField> sqlFieldList, String createSQL) {
                Log.e("Ellen创建表成功",createSQL);
                List<Student> studentList = new ArrayList<>();
                studentList.add(new Student(1,"ellen1","男","18272167571",true,"A"));
                studentList.add(new Student(2,"ellen2","女","18272167572",false,"B"));
                studentList.add(new Student(3,"ellen3","男","18272167573",true,"C"));
                studentList.add(new Student(4,"ellen4","男","18272167574",true,"D"));
                studentList.add(new Student(5,"ellen5","女","18272167575",false,"E"));
                zxyReflectionTable.saveData(studentList);
            }
        });

        String orderSQL = Order.getInstance(false).setFirstOrderFieldName("id").setIsDesc(true).createSQL();
        List<Student> studentList = zxyReflectionTable.getAllDatas(orderSQL);
        for(Student student:studentList){
            Log.e("查询的数据",student.getName()+":"+student.getId()+":"+student.isMan()+":"+student.getNameOne()+":"+student.getTeacher().getPhoneNumber());
        }
    }

}
