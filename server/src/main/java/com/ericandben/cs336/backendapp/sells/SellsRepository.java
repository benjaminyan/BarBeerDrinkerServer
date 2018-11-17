package com.ericandben.cs336.backendapp.sells;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface SellsRepository extends PagingAndSortingRepository<Sells, SellsKey> {

}