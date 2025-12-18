package com.banquito.sucursal.repository;

import com.banquito.sucursal.model.Branch;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BranchRepository extends MongoRepository<Branch, String> {
}
