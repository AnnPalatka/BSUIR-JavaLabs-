package com.example.lab.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestModel {

    @JsonProperty("real")

    private Double real;

    @JsonProperty("image")

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
