package com.example.lab.Entities;

public class ComplexNumberEntity {

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

    public ComplexNumberEntity(Double real, Double image) {
        this.real = real;
        this.image = image;
    }
}
