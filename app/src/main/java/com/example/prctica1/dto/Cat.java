package com.example.prctica1.dto;

import com.google.gson.annotations.SerializedName;

public class Cat {
    private String fact;
    private int lenght;

    public Cat(String fact, int lenght) {
        this.fact = fact;
        this.lenght = lenght;
    }

    public String getFact() {
        return fact;
    }

    public void setFact(String fact) {
        this.fact = fact;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }
}
