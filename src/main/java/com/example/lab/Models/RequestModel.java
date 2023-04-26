package com.example.lab.Models;

public class RequestModel {


    private Double real;
    private Double image;

    public Double getReal() {
        return real;
    }

    public void setReal(Double real) {
        this.real = real;
    }

    public Double getImage() {
        return image;
    }

    public void setImage(Double image) {
        this.image = image;
    }

    public int hashCode()
    {
        int hash = (int) (real*1000000 + image*1000000);
        return hash;
    }

    public boolean equals(Object other)
    {
        return this.hashCode() == other.hashCode();
    }
}
