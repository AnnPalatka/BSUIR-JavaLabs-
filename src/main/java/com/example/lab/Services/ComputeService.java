package com.example.lab.Services;

import com.example.lab.Entities.ComplexEntity;
import com.example.lab.Entities.ComplexNumberEntity;
import com.example.lab.Entities.OperationEntity;
import com.example.lab.Interfaces.Repositories.OperationRepository;
import com.example.lab.Models.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class ComputeService {

    @Autowired
    private OperationRepository operationRepository;

    public ComplexEntity compute(Double a, Double b)
    {
        var resultModule = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

        var resultPhase = Math.acos( a / resultModule);

        var entity = new OperationEntity();
        entity.setReal(a);
        entity.setImage(b);
        entity.setPhase(resultPhase);
        entity.setModule(resultModule);

        operationRepository.save(entity);

        return new ComplexEntity(resultModule, resultPhase);
    }

    public ArrayList<ComplexEntity> compute(ArrayList<ComplexNumberEntity> data)
    {
        return data.stream().parallel().map(m ->
                compute(m.getReal(), m.getImage())).collect(Collectors.toCollection(ArrayList::new));

    }
}
