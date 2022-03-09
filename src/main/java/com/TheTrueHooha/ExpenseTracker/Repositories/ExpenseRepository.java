package com.TheTrueHooha.ExpenseTracker.Repositories;

import com.TheTrueHooha.ExpenseTracker.Model.Expenses;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface ExpenseRepository extends MongoRepository<Expenses, Long> {

    @Query("{'name': ?0}")
    Optional<Expenses> findByName(String name);
}
