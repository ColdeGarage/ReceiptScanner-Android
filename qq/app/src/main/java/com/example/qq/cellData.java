package com.example.qq;

public class cellData {
    private int amount = 1;
    private String item = "what";
    private String name = "x";
    public cellData(int amount,String item, String name) {
        this.amount = amount;
        this.item = item;
        this.name = name;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setItem(String item) {this.item = item;}

    public int getAmount() {
        return this.amount;
    }
    public String getitem() {
        return this.item;
    }
    public String getName() {
        return this.name;
    }

}
