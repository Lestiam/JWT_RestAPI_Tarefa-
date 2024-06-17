package com.example.JWT_RestAPI3.repository;

import com.example.JWT_RestAPI3.model.LoginRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthRepository extends MongoRepository<LoginRequest, String> {
    LoginRequest findByUsername(String username);
}
