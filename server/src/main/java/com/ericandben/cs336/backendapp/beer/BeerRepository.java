package com.ericandben.cs336.backendapp.beer;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BeerRepository extends PagingAndSortingRepository<Beer, String> {

}