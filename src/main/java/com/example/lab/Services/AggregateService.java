package com.example.lab.Services;

import com.example.lab.Entities.ComplexEntity;
import com.example.lab.Models.AggregateModel;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class AggregateService {

    public static AggregateModel Aggregate(ArrayList<ComplexEntity> results)
    {
        var phases = results.stream().parallel().map(ComplexEntity::getPhase).collect(Collectors.toCollection(ArrayList::new));
        var middlePhase = phases.stream().reduce(Double::sum).get() / phases.size();

        var modules = results.stream().parallel().map(ComplexEntity::getModule).collect(Collectors.toCollection(ArrayList::new));
        var middleModule = modules.stream().reduce(Double::sum).get() / modules.size();

        Double minPhase = null, maxPhase = null, minModule = null, maxModule = null;
        // max + min

        return new AggregateModel(middlePhase, middleModule, maxPhase, minPhase, maxModule, minModule);
    }
}
