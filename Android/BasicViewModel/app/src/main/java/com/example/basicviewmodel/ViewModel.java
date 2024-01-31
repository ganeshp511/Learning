package com.example.basicviewmodel;

public class ViewModel extends androidx.lifecycle.ViewModel {
    int counter=0;
    public void increaseCounter(){
        counter=counter+10;
    }
    public int getCounter(){
        return counter;
    }
}
