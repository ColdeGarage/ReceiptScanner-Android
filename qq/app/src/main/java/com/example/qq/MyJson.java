package com.example.qq;

public class MyJson {
    private int id;
    private int money;
    private String Y_M_D;
    private String M;
    private String D;
    private String type;
    private String remark;

    public void setId(int id) {
        this.id = id;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setD(String d) {
        D = d;
    }

    public void setM(String m) {
        M = m;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setY_M_D(String y_M_D) {
        Y_M_D = y_M_D;
    }

    public int getId() {
        return id;
    }

    public int getMoney() {
        return money;
    }

    public String getD() {
        return D;
    }

    public String getM() {
        return M;
    }

    public String getRemark() {
        return remark;
    }
    public String getType() {
        return type;
    }

    public String getY_M_D() {
        return Y_M_D;
    }
}
