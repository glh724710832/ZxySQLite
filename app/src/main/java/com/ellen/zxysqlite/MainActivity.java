package com.ellen.zxysqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
        String whereSql = Where.getInstance(false)
                .addAndWhereValue("id",WhereSymbolEnum.LESS_THAN,4)
                .addAndWhereValue("name",WhereSymbolEnum.LIKE,"ss")
                .createSQL();
        String sql = SerachTableData.getInstance()
                .setTableName("student")
                .addSelectField("id")
                .addSelectField("name")
                .createSQLAutoWhere(whereSql);
        textView.setText(sql);
    }
}
