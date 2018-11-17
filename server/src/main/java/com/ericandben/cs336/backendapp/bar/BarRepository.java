package com.ericandben.cs336.backendapp.bar;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface BarRepository extends CrudRepository<Bar, Integer>, BarRepositoryCustom  {
    public Bar findByName(String name);
    

    @Query(value = "SELECT sum(t.amountPaid)" +
    " FROM Transaction t" +
    " WHERE t.bar.name = :barName AND FUNC('HOUR',t.dateTime) BETWEEN :beginTime AND :endTime "
    + "AND t.dateTime BETWEEN :beginDate AND :endDate")
    public Double timeDistSalesPerBar(String barName,Date beginDate, Date endDate, String beginTime, String endTime);
}