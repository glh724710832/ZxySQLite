package com.ellen.zxysqlite;

import com.ellen.zxysqlite.table.reflection.NotNull;
import com.ellen.zxysqlite.table.reflection.Primarykey;

public class Phone {

    @Primarykey
    private int id;
    private String xinHao;
    @NotNull
    private String ciCun;
    private float price;
    private System system;
    private boolean isTag = false;

    public Phone(int id, String xinHao, String ciCun, float price, System system) {
        this.id = id;
        this.xinHao = xinHao;
        this.ciCun = ciCun;
        this.price = price;
        this.system = system;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getXinHao() {
        return xinHao;
    }

    public void setXinHao(String xinHao) {
        this.xinHao = xinHao;
    }

    public String getCiCun() {
        return ciCun;
    }

    public void setCiCun(String ciCun) {
        this.ciCun = ciCun;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", xinHao='" + xinHao + '\'' +
                ", ciCun='" + ciCun + '\'' +
                ", price=" + price +
                ", system=" + system.toString() +
                '}';
    }
}
