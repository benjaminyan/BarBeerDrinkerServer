package com.ericandben.cs336.backendapp.bar;

import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface BarRepository extends CrudRepository<Bar, Integer> {
    public Bar findByName(String name);
}