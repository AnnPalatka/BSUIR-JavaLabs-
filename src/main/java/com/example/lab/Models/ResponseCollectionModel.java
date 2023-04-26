package com.example.lab.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class ResponseCollectionModel {
    @JsonProperty("collection")
    private Collection<ResponseModel> collection;

    public Collection<ResponseModel> getCollection() {
        return collection;
    }

    public void setCollection(Collection<ResponseModel> collection) {
        this.collection = collection;
    }

    public ResponseCollectionModel(Collection<ResponseModel> collection) {
        this.collection = collection;
    }

    public ResponseCollectionModel() {
    }

    private Double middlePhase;

    private Double middleModule;

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
}
