package com.ericandben.cs336.backendapp.bar;

import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import com.ericandben.cs336.backendapp.transaction.Transaction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BarRepository extends PagingAndSortingRepository<Bar, Integer>, BarRepositoryCustom  {
    
    public Bar findByName(String name);
    

    @Query(value = "SELECT sum(t.amountPaid)" +
    " FROM Transaction t" +
    " WHERE t.bar.name = :barName AND t.tTime BETWEEN :beginTime AND :endTime "
    + "AND t.dateTime BETWEEN :beginDate AND :endDate")
    public Double timeDistSalesPerBar(String barName,Date beginDate, Date endDate, Time beginTime, Time endTime);
}