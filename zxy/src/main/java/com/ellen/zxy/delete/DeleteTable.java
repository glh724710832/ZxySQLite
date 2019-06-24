package com.ellen.zxy.delete;

import com.ellen.ellensqlite.helper.BaseSql;

import java.util.ArrayList;
import java.util.List;

public class DeleteTable extends BaseSql {
    private List<String> tableNameList;

    public static DeleteTable getInstance() {
        return new DeleteTable();
    }

    public DeleteTable addTableName(String tableName) {
        if (tableNameList == null) {
            tableNameList = new ArrayList<>();
        }
        tableNameList.add(tableName);
        return this;
    }

    public String createSQL() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("DROP TABLE ");
        stringBuilder.append(getStringSQL(tableNameList));
        stringBuilder.append(";");
        return stringBuilder.toString();
    }

}
