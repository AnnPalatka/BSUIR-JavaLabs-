package com.example.lab.Services;

import com.example.lab.Entities.ComplexEntity;
import com.example.lab.Entities.ComplexNumberEntity;
import com.example.lab.Entities.OperationEntity;
import com.example.lab.Interfaces.Repositories.OperationRepository;
import com.example.lab.Models.ResponseModel;
import com.example.lab.TestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class ComputeService {

    private final Logger logger = LoggerFactory.getLogger(ComputeService.class);
    @Autowired
    private OperationRepositoryService operationRepositoryService;

    public ComplexEntity compute(Double a, Double b)
    {
        var resultModule = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));

        var resultPhase = Math.acos( a / resultModule);

        return new ComplexEntity(resultModule, resultPhase);
    }

    public ArrayList<ComplexEntity> compute(ArrayList<ComplexNumberEntity> data)
    {
        return data.stream().parallel().map(m ->
                compute(m.getReal(), m.getImage())).collect(Collectors.toCollection(ArrayList::new));
    }

    public CompletableFuture<ComplexEntity> computeAsync(OperationEntity entity)
    {
        return CompletableFuture.supplyAsync(() -> {
            try {
                var result = compute(entity.getReal(), entity.getImage());

                Thread.sleep(7000);
                operationRepositoryService.update(entity, entity.getReal(), entity.getImage(), result.getPhase(), result.getModule());
                logger.info("computed");

                return result;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
