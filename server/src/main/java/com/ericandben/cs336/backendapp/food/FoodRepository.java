package com.ericandben.cs336.backendapp.food;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FoodRepository extends PagingAndSortingRepository<Food, String> {

}