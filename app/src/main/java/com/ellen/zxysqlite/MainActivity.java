package com.ellen.zxysqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ellen.zxysqlite.create.createtable.CreateTable;
import com.ellen.zxysqlite.create.createtable.Field;
import com.ellen.zxysqlite.order.Order;
import com.ellen.zxysqlite.serach.SerachTableData;
import com.ellen.zxysqlite.where.Where;
import com.ellen.zxysqlite.helper.WhereSymbolEnum;
import com.ellen.zxysqlite.where.WhereIn;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv);
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
                .addField(Field.getPrimaryKeyField("id","int",true))
                .addField(Field.getNotNullContainsDefaultValueField("name","text","阿三"))
                .addField(Field.getOrdinaryField("sex","text"))
                .createSQL();
        textView.setText(sql);
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(this,"library",null,1);
        Student student = new Student(mySQLiteHelper.getWritableDatabase());
    }
}
