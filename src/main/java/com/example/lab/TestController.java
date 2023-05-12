package com.example.lab;

import com.example.lab.Entities.ComplexNumberEntity;
import com.example.lab.Entities.OperationEntity;
import com.example.lab.Interfaces.Repositories.OperationRepository;
import com.example.lab.Models.RequestCollectionModel;
import com.example.lab.Models.RequestModel;
import com.example.lab.Models.ResponseCollectionModel;
import com.example.lab.Models.ResponseModel;
import com.example.lab.Services.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

@RestController
public class TestController {
    private final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private ComputeService computeService;

    @Autowired
    private OperationRepositoryService operationRepositoryService;

    @Autowired
    private CacheService<RequestModel, ResponseModel> cacheService;

    @Autowired
    private CounterService counterService;

    @GetMapping("/compute")
    public ResponseModel compute(@ModelAttribute RequestModel model)
    {
        counterService.Add();

        if (model.getImage()<0) {
            logger.info("Wrong argument");
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Error");
        }

        ResponseModel response = cacheService.Get(model);
        if(response != null)
            return response;

        var complexEntity = computeService.compute(model.getReal(), model.getImage());

        operationRepositoryService.create(model.getReal(), model.getImage(), complexEntity.getPhase(), complexEntity.getModule());

        response = new ResponseModel(complexEntity.getPhase(), complexEntity.getModule());

        cacheService.Push(model, response);

        return response;
    }

    @PostMapping(value="/compute/collection", consumes = "application/json", produces = "application/json")
    public ResponseCollectionModel computeCollection(@RequestBody ArrayList<RequestModel> model)
    {
        counterService.Add();

        if (!model.stream().parallel().allMatch(m -> m.getImage() > 0)) {
            logger.info("Wrong argument");
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Error");
        }

        var data = model.stream().parallel().map(x -> new ComplexNumberEntity(x.getReal(), x.getImage()))
                .collect(Collectors.toCollection(ArrayList::new));

        var results = computeService.compute(data);

        var aggregateModel = AggregateService.Aggregate(results);

        operationRepositoryService.createCollection(data, results);

        var resultModels = results.stream().parallel().map(x -> new ResponseModel(x.getPhase(), x.getModule()))
                .collect(Collectors.toCollection(ArrayList::new));

        return new ResponseCollectionModel(resultModels, aggregateModel);
    }

    @PostMapping(value="/async/compute", consumes = "application/json", produces = "application/json")
    public Integer computeAsync(@RequestBody RequestModel model)
    {
        var entity = operationRepositoryService.create(model.getReal(), model.getImage());

        computeService.computeAsync(entity);

        return entity.getId();
    }

    @GetMapping("/async/result")
    public ResponseModel asyncResult(Integer entityId)
    {
        var entity = operationRepositoryService.getById(entityId);

        return new ResponseModel(entity.getPhase(), entity.getModule());
    }

    @GetMapping("/stat")
    public Integer stat()
    {
       return counterService.GetCount();
    }
}
