package com.ellen.zxy.delete;

/**
 * 删除表的某一列
 */
public class DeleteTableColumn {
    private String tableName;
    private String columnName;

    public static DeleteTableColumn getInstance(){
        return new DeleteTableColumn();
    }

    public DeleteTableColumn addColumnName(String columnName){
        this.columnName = columnName;
        return this;
    }

    public DeleteTableColumn addTableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ALTER TABLE ");
        stringBuilder.append(tableName+" ");
        stringBuilder.append("DROP COLUMN ");
        stringBuilder.append(columnName+";");
        return stringBuilder.toString();
    }
}
