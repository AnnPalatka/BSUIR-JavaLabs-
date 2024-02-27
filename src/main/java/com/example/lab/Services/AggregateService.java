package com.example.lab.Services;

import com.example.lab.Entities.ComplexEntity;
import com.example.lab.Models.AggregateModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class AggregateService {

    public static AggregateModel Aggregate(ArrayList<ComplexEntity> results)
    {
        var phases = results.stream().parallel().map(ComplexEntity::getPhase).collect(Collectors.toCollection(ArrayList::new));
        var middlePhase = phases.stream().reduce(Double::sum).get() / phases.size();

        var modules = results.stream().parallel().map(ComplexEntity::getModule).collect(Collectors.toCollection(ArrayList::new));
        var middleModule = modules.stream().reduce(Double::sum).get() / modules.size();

        var modulesMax = results.stream().parallel().map(ComplexEntity::getModule).collect(Collectors.toCollection(ArrayList::new));
        var maxModule = Collections.max(modulesMax);

        var modulesMin = results.stream().parallel().map(ComplexEntity::getModule).collect(Collectors.toCollection(ArrayList::new));
        var minModule = Collections.min(modulesMax);

        var phasesMax = results.stream().parallel().map(ComplexEntity::getPhase).collect(Collectors.toCollection(ArrayList::new));
        var maxPhase = Collections.max(phasesMax);

        var phasesMin = results.stream().parallel().map(ComplexEntity::getPhase).collect(Collectors.toCollection(ArrayList::new));
        var minPhase = Collections.min(phasesMin);

        return new AggregateModel(middlePhase, middleModule, maxPhase, minPhase, maxModule, minModule);
    }
}
