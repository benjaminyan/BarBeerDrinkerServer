package com.ericandben.cs336.backendapp.softdrink;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SoftDrinkRepository extends PagingAndSortingRepository<SoftDrink, String> {

}