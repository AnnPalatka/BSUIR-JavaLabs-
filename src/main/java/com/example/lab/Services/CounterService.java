package com.example.lab.Services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component(value="counter")
@Scope("singleton")
public class CounterService {
    private static int count;

    private final Lock lock = new ReentrantLock();


    public void Add()
    {
        lock.lock();
        count++;
        lock.unlock();
    }

    public int GetCount()
    {
        return count;
    }
}