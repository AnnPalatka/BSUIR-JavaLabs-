package com.example.lab;

import com.example.lab.Services.ComputeService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = LabApplication.class)
public class ComputeTest {
    @Test
    public void Test()
    {
        var service = new ComputeService();
        var result = service.compute(2.0,0.5);
        assertEquals(result.getPhase(), 0.24497866312686423);
        assertEquals(result.getModule(), 2.0615528128088303);
    }
}
