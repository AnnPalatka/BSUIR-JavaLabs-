package com.example.lab.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class RequestCollectionModel {

    public RequestCollectionModel(Collection<RequestModel> _collection) {
        collection = _collection;
    }

    @JsonProperty("collection")
    public Collection<RequestModel> collection;

}
