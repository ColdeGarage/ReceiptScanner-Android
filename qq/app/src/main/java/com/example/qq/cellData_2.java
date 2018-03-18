package com.example.qq;

import android.support.annotation.NonNull;

public class cellData_2 implements Comparable {
    private int total;
    private String item;
    public cellData_2(int total,String item) {
        this.item = item;
        this.total = total;
    }
    public int getTotal() {
        return this.total;
    }
    public String getItem() {
        return this.item;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public void setItem(String item) {
        this.item = item;
    }


    @Override
    public int compareTo(@NonNull Object o) {
        cellData_2 obj = (cellData_2) o;
        if (this.total>obj.total)
            return 1;
        else return -1;
    }
}