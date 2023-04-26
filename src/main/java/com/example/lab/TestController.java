package com.example.lab;

import com.example.lab.Models.RequestModel;
import com.example.lab.Models.ResponseModel;
import com.example.lab.Services.CacheService;
import com.example.lab.Services.ComputeService;
import com.example.lab.Services.CounterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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

    @GetMapping("/stat")
    public Integer stat()
    {
       return counterService.GetCount();
    }
}
