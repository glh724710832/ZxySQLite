package com.ellen.zxysqlite.update;

public class UpdateTableName {
    private String oldTableName;
    private String newTableName;

    public static UpdateTableName getInstance(){
        return new UpdateTableName();
    }

    public UpdateTableName addOldTableName(String oldTableName){
        this.oldTableName = oldTableName;
        return this;
    }

    public UpdateTableName addNewTableName(String newTableName){
        this.newTableName = newTableName;
        return this;
    }

    public String createSQL(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("ALTER TABLE ");
        stringBuilder.append("'"+oldTableName+"' RENAME TO ");
        stringBuilder.append("'"+newTableName+"';");
        return stringBuilder.toString();
    }
}
