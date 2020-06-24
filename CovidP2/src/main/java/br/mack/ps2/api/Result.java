package br.mack.ps2.api;

import java.io.Serializable;

public class Result implements Serializable {
    String date;
    int value;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }




}
