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
}
