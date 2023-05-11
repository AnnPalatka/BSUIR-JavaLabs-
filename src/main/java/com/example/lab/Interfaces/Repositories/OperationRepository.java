package com.example.lab.Interfaces.Repositories;

import com.example.lab.Entities.OperationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepository extends CrudRepository<OperationEntity, Integer> { }