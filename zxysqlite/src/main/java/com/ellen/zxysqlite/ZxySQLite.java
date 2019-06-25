package com.ellen.zxysqlite;

import com.ellen.zxysqlite.add.AddManyRowToTable;
import com.ellen.zxysqlite.add.AddSingleRowToTable;
import com.ellen.zxysqlite.add.AddTableColumn;
import com.ellen.zxysqlite.delete.DeleteTable;
import com.ellen.zxysqlite.delete.DeleteTableDataRow;
import com.ellen.zxysqlite.order.Order;
import com.ellen.zxysqlite.serach.SerachTableData;
import com.ellen.zxysqlite.serach.SerachTableExist;
import com.ellen.zxysqlite.update.UpdateTableDataRow;
import com.ellen.zxysqlite.update.UpdateTableName;
import com.ellen.zxysqlite.where.Between;
import com.ellen.zxysqlite.where.Where;
import com.ellen.zxysqlite.where.WhereIn;

public abstract class ZxySQLite {

    /**
     * 删除表SQL语句生产类
     * @return
     */
    public DeleteTable getDeleteTable() {
        return DeleteTable.getInstance();
    }
    /**
     * 动态添加表的列的SQL语句生产类
     * @return
     */
    public AddTableColumn getAddTableColumn() {
        return AddTableColumn.getInstance();
    }
    /**
     * 修改表的名字的SQL语句生产类
     * @return
     */
    public UpdateTableName getUpdateTableName() {
        return UpdateTableName.getInstance();
    }
    /**
     * 查询表是否存在的SQL语句生产类
     */
    public SerachTableExist getSerachTableExist() {
        return SerachTableExist.getInstance();
    }
    /**
     * 增加数据（单条）的SQL语句生产类
     */
    public AddSingleRowToTable getAddSingleRowToTable() {
        return AddSingleRowToTable.getInstance();
    }
    /**
     * 增加数据（多条）的SQL语句生产类
     */
    public AddManyRowToTable getAddManyRowToTable() {
        return AddManyRowToTable.getInstance();
    }
    /**
     * 删除数据的SQL语句生产类
     */
    public DeleteTableDataRow getDeleteTableDataRow() {
        return DeleteTableDataRow.getInstance();
    }
    /**
     * 修改数据的SQL语句生产类
     */
    public UpdateTableDataRow getUpdateTableDataRow() {
        return UpdateTableDataRow.getInstance();
    }
    /**
     * 查询数据的SQL语句生产类
     */
    public SerachTableData getSerachTableData() {
        return SerachTableData.getInstance();
    }
    /**
     * 普通where生产
     * example:
     * WHERE id = 5 AND name = 'Ellen'
     * @param isContainsWhere 生产出的Between是否包含Where
     *
     */
    public Where getWhere(boolean isContainsWhere) {
        return Where.getInstance(isContainsWhere);
    }

    /**
     * WhereIn生产的SQL语句生产类
     * example:
     * WHERE name IN ('李一','王二','张三')
     * @param isContainsWhere 生产出的Between是否包含Where
     * @return
     */
    public WhereIn getWhereIn(boolean isContainsWhere) {
        return WhereIn.getInstance(isContainsWhere);
    }

    /**
     * Between语句生产类
     * example:
     * BETWEEN 3 AND 8
     * @param isContainsWhere 生产出的Between是否包含Where
     * @return
     */
    public Between getBetween(boolean isContainsWhere) {
        return Between.getInstance(isContainsWhere);
    }

    /**
     * 排序Order生成的SQL语句生产类
     * @param isContainsOrderBy 生产出的Order是否包含ORDER BY
     * @return
     */
    public Order getOrder(boolean isContainsOrderBy) {
        return Order.getInstance(isContainsOrderBy);
    }
}
