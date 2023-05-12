package com.example.lab.Services;

import com.example.lab.Entities.ComplexEntity;
import com.example.lab.Entities.ComplexNumberEntity;
import com.example.lab.Entities.OperationEntity;
import com.example.lab.Interfaces.Repositories.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OperationRepositoryService {
    @Autowired
    private OperationRepository operationRepository;

    public OperationEntity create(Double real, Double image)
    {
        var entity = new OperationEntity();
        entity.setReal(real);
        entity.setImage(image);

        operationRepository.save(entity);

        return entity;
    }

    public OperationEntity create(Double real, Double image, Double phase, Double module)
    {
        var entity = new OperationEntity();
        entity.setReal(real);
        entity.setImage(image);
        entity.setPhase(phase);
        entity.setModule(module);

        operationRepository.save(entity);

        return entity;
    }

    public void createCollection(ArrayList<ComplexNumberEntity> data, ArrayList<ComplexEntity> results)
    {
        for(int i = 0; i < data.size(); i++)
        {
            var requestData = data.get(i);
            var resultData = results.get(i);
            create(requestData.getReal(), requestData.getImage(), resultData.getPhase(), resultData.getModule());
        }
    }

    public void update(OperationEntity entity, Double real, Double image, Double phase, Double module)
    {
        entity.setReal(real);
        entity.setImage(image);
        entity.setPhase(phase);
        entity.setModule(module);

        operationRepository.save(entity);
    }

    public OperationEntity getById(Integer id)
    {
        return operationRepository.findById(id).get();
    }
}
