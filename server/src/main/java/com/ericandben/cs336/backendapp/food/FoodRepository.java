package com.ericandben.cs336.backendapp.food;

import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface FoodRepository extends CrudRepository<Food, Integer> {

}