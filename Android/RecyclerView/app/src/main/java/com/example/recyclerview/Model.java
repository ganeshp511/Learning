package com.example.recyclerview;

public class Model {
    String item1;
    String item2;

    public String getItem1() {
        return item1;
    }

    public void setItem1(String item1) {
        this.item1 = item1;
    }

    public String getItem2() {
        return item2;
    }

    public void setItem2(String item2) {
        this.item2 = item2;
    }

    public Model(String item1, String item2) {
        this.item1 = item1;
        this.item2 = item2;
    }
}
