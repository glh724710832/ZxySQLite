package com.ellen.zxysqlite;

import android.content.res.Configuration;
import android.os.Environment;
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

import java.io.File;
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
                .addField(SQLField.getPrimaryKeyField("id", "int", true))
                .addField(SQLField.getNotNullContainsDefaultValueField("name", "text", "阿三"))
                .addField(SQLField.getOrdinaryField("sex", "text"))
                .createSQLIfNotExists();
        //textView.setText(sql);
        initView();

    }


    private void initView() {
        textView = findViewById(R.id.tv);
        File file = new File(Environment.getExternalStorageDirectory(),"ZxySQLite");
        if(!file.exists()){
            file.mkdirs();
        }
        MySQLiteHelper mySQLiteHelper = new MySQLiteHelper(this,file.getAbsolutePath(),"my",1);
        final PhoneSQLite phoneSQLite = new PhoneSQLite(mySQLiteHelper.getWriteDataBase(), Phone.class);
        phoneSQLite.onCreateTable(new ZxyReflectionTable.OnCreateSQLiteCallback() {
            @Override
            public void onCreateTableBefore(String tableName, List<SQLField> sqlFieldList, String createSQL) {

            }

            @Override
            public void onCreateTableFailure(String errMessage, String tableName, List<SQLField> sqlFieldList, String createSQL) {

            }

            @Override
            public void onCreateTableSuccess(String tableName, List<SQLField> sqlFieldList, String createSQL) {
                Log.e("Ellen->建表成功", createSQL);
                List<Phone> phoneList = new ArrayList<>();
                System system1 = new System("Android", 4);
                System system2 = new System("WindowsPhone", 8);
                System system3 = new System("Android", 6);
                System system4 = new System("IOS", 20);
                System system5 = new System("Android", 8);
                Phone phone1 = new Phone(1, "A-1", "5寸", 1799.9f, system1);
                Phone phone2 = new Phone(2, "A-2", "10寸", 1899.7f, system2);
                Phone phone3 = new Phone(3, "A-3", "8寸", 2799.6f, system3);
                Phone phone4 = new Phone(4, "A-4", "7寸", 8799.6f, system4);
                Phone phone5 = new Phone(5, "A-5", "6寸", 111799.3f, system5);
                phoneList.add(phone1);
                phoneList.add(phone2);
                phoneList.add(phone3);
                phoneList.add(phone4);
                phoneList.add(phone5);
                phoneSQLite.saveData(phoneList);
            }
        });
        String whereSQL = phoneSQLite.getWhereIn(false)
                .setFieldName("xinHao")
                .addInValue("加密"+"A-3")
                .addInValue("加密"+"A-4")
                .addInValue("加密"+"A-5")
                .createSQLWhereIn();
        String orderSQL = Order.getInstance(false).setFirstOrderFieldName("id").setIsDesc(false).createSQL();
        List<Phone> phoneList = phoneSQLite.serach(whereSQL,orderSQL);
        for (Phone phone : phoneList) {
            Log.e("查询的数据", phone.toString());
        }

    }
}
