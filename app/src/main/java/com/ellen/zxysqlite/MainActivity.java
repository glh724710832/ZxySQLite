package com.ellen.zxysqlite;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ellen.zxysqlite.createsql.create.createtable.CreateTable;
import com.ellen.zxysqlite.createsql.create.createtable.SQLField;
import com.ellen.zxysqlite.singletable.StudentReflectionTable;
import com.ellen.zxysqlite.table.reflection.ZxyReflectionTable;

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
        ZxyReflectionTable zxyReflectionTable = new StudentReflectionTable(mySQLiteHelper.getReadableDatabase(),Student.class,"my_student");
        zxyReflectionTable.onCreateTableIfNotExits();
    }

}
