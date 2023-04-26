package com.example.lab.Services;

import com.example.lab.Entities.ComplexEntity;
import org.springframework.stereotype.Service;

@Service
public class ComputeService {
    public ComplexEntity compute(Double a, Double b)
    {
        var resultModule = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

        var resultPhase = Math.acos( a / resultModule);

        return new ComplexEntity(resultModule, resultPhase);
    }
}
