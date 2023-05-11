package com.example.lab.Models;

public class AggregateModel {


    private Double middlePhase;
    private Double middleModule;

    private Double maxPhase;
    private Double minPhase;

    private Double maxModule;
    private Double minModule;

    public Double getMiddlePhase() {
        return middlePhase;
    }

    public void setMiddlePhase(Double middlePhase) {
        this.middlePhase = middlePhase;
    }

    public Double getMiddleModule() {
        return middleModule;
    }

    public void setMiddleModule(Double middleModule) {
        this.middleModule = middleModule;
    }

    public Double getMaxPhase() {
        return maxPhase;
    }

    public void setMaxPhase(Double maxPhase) {
        this.maxPhase = maxPhase;
    }

    public Double getMinPhase() {
        return minPhase;
    }

    public void setMinPhase(Double minPhase) {
        this.minPhase = minPhase;
    }

    public Double getMaxModule() {
        return maxModule;
    }

    public void setMaxModule(Double maxModule) {
        this.maxModule = maxModule;
    }

    public Double getMinModule() {
        return minModule;
    }

    public AggregateModel(Double middlePhase, Double middleModule, Double maxPhase, Double minPhase, Double maxModule, Double minModule) {
        this.middlePhase = middlePhase;
        this.middleModule = middleModule;
        this.maxPhase = maxPhase;
        this.minPhase = minPhase;
        this.maxModule = maxModule;
        this.minModule = minModule;
    }

    public void setMinModule(Double minModule) {
        this.minModule = minModule;
    }
}
