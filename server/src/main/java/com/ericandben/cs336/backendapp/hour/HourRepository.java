package com.ericandben.cs336.backendapp.hour;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface HourRepository extends PagingAndSortingRepository<Hour, HourKey> {

}