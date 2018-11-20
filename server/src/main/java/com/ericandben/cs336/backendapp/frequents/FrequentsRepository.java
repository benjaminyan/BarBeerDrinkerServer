package com.ericandben.cs336.backendapp.frequents;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface FrequentsRepository extends PagingAndSortingRepository<Frequents, FrequentsKey> {

}