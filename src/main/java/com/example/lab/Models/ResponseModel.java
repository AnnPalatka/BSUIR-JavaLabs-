package com.example.lab.Models;

public class ResponseModel {
    public Double getPhase() {
        return phase;
    }

    public void setPhase(Double phase) {
        this.phase = phase;
    }

    public Double getModule() {
        return module;
    }

    public void setModule(Double module) {
        this.module = module;
    }

    private Double phase;
    private Double module;



    public ResponseModel(Double phase, Double module)
    {
        this.phase = phase;
        this.module = module;
    }
}
