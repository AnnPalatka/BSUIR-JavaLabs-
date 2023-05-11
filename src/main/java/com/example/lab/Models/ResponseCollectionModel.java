package com.example.lab.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

public class ResponseCollectionModel {
    @JsonProperty("results")
    private Collection<ResponseModel> results;

    @JsonProperty("aggregatedResults")

    private AggregateModel aggregatedResults;

    public Collection<ResponseModel> getResults() {
        return results;
    }

    public void setResults(Collection<ResponseModel> results) {
        this.results = results;
    }

    public AggregateModel getAggregatedResults() {
        return aggregatedResults;
    }

    public ResponseCollectionModel(Collection<ResponseModel> results, AggregateModel aggregatedResults) {
        this.results = results;
        this.aggregatedResults = aggregatedResults;
    }

    public void setAggregatedResults(AggregateModel aggregatedResults) {
        this.aggregatedResults = aggregatedResults;
    }
}
