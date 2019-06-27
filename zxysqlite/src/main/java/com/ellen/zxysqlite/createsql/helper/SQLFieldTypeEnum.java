package com.ellen.zxysqlite.createsql.helper;

public enum SQLFieldTypeEnum {

    INTEGER("integer"),
    BIG_INT("bigint"),
    TEXT("text"),
    REAL("real"),
    BLOB("blob"),
    NUMERIC("numeric"),
    DATE("date");

    private String typeName;

    SQLFieldTypeEnum(String typeName){
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
