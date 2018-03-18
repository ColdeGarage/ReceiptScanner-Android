package com.example.qq;
public class cellData_3 {
    private String code = "what";
    private String date = "x";
    public cellData_3(String date, String code) {
        this.date = date;
        this.code = code;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setCode(String code) {this.code = code;}

    public String getCode() {
        return this.date;
    }
    public String getDate() {
        return this.code;
    }
}
