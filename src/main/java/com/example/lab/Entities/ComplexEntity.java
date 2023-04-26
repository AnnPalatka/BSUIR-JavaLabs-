package com.example.lab.Entities;

public class ComplexEntity {
    public Double getModule() {
        return module;
    }

    public void setModule(Double module) {
        this.module = module;
    }

    public ComplexEntity(Double module, Double phase) {
        this.module = module;
        this.phase = phase;
    }

    public Double getPhase() {
        return phase;
    }

    public void setPhase(Double phase) {
        this.phase = phase;
    }

    private Double module;

    private Double phase;
}
