package com.ellen.zxysqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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
        String sql = Where.getInstance(false)
                .addAndWhereValue("id1",WhereSymbolEnum.LESS_THAN,24)
                .addAndWhereValue("id2",WhereSymbolEnum.LESS_THAN,24)
                .addAndWhereValue("id3",WhereSymbolEnum.LESS_THAN,24)
                .addAndWhereValue("id4",WhereSymbolEnum.LESS_THAN,24)
                .addAndWhereValue("id5",WhereSymbolEnum.LESS_THAN,24)
                .createSQL();
        textView.setText(sql);
    }
}
