package com.ellen.zxysqlite.helper;

public enum FieldTypeEnum {

    TEXT("TEXT"),
    INT("INT");

    private String dataType = "";

    FieldTypeEnum(String dataType){
        this.dataType = dataType;
    }

}
