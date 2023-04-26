package com.example.lab;

import com.example.lab.Models.RequestCollectionModel;
import com.example.lab.Models.RequestModel;
import com.example.lab.Models.ResponseCollectionModel;
import com.example.lab.Models.ResponseModel;
import com.example.lab.Services.CacheService;
import com.example.lab.Services.ComputeService;
import com.example.lab.Services.CounterService;
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

        response = new ResponseModel(complexEntity.getPhase(), complexEntity.getModule());

        cacheService.Push(model, response);

        return response;
    }

    @PostMapping(value="/compute/collection", consumes = "application/json", produces = "application/json")
    public ResponseCollectionModel computeCollection(@RequestBody ArrayList<RequestModel> data)
    {
        counterService.Add();

        if (!data.stream().parallel().allMatch(m -> m.getImage() > 0)) {
            logger.info("Wrong argument");
            throw new ResponseStatusException(HttpStatusCode.valueOf(400), "Error");
        }

        var array = data.stream().parallel().map(m ->
        {
            ResponseModel tmp = cacheService.Get(m);
            if(tmp != null)
                return tmp;

            var complexEntity = computeService.compute(m.getReal(), m.getImage());

            tmp = new ResponseModel(complexEntity.getPhase(), complexEntity.getModule());

            cacheService.Push(m, tmp);

            return tmp;

        }).collect(Collectors.toCollection(ArrayList::new));

        return new ResponseCollectionModel(array);
    }

    @GetMapping("/stat")
    public Integer stat()
    {
       return counterService.GetCount();
    }
}
