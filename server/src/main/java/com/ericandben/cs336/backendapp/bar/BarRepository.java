package com.ericandben.cs336.backendapp.bar;

import java.util.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ericandben.cs336.backendapp.transaction.Transaction;
import com.ericandben.cs336.backendapp.beer.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

class BeerWithSales {
    String beer;
    Integer sales;

    public BeerWithSales(String beer, Integer sales) {
        this.beer = beer;
        this.sales = sales;
    }
}

public interface BarRepository extends PagingAndSortingRepository<Bar, Integer>, BarRepositoryCustom  {
    
    public Bar findByName(String name);

    public Page<Bar> findAll(Pageable pageable);
    

    @Query(value = "SELECT sum(t.amountPaid)" +
    " FROM Transaction t" +
    " WHERE t.bar.name = :barName AND t.tTime BETWEEN :beginTime AND :endTime "
    + "AND t.dateTime BETWEEN :beginDate AND :endDate")
    public Double timeDistSalesPerBar(String barName,Date beginDate, Date endDate, Time beginTime, Time endTime);

    /* 
select i.item, sum(i.quantity) as numSold from Includes i
where i.bar = 'Academia'
and i.item in (select name from Beers)
group by i.item
order by numSold desc;
    */

    /*
    @Query(value = "SELECT i.item.name, sum(i.quantity) AS total" +
    " FROM Includes i WHERE i.bar.name = :barName AND TYPE(i.item) = 'Beer'" + 
    " GROUP by i.item.name ORDER BY total desc"
    )
    */

    // Don't leave out transaction! If we do, we'll get an error saying
    // could not resolve property: name of: com.ericandben.cs336.backendapp.includes.Includes
    @Query(value = "SELECT i.item.name, sum(i.quantity)" +
    " FROM Includes i JOIN i.transaction.bar.itemsSold s WHERE i.transaction.bar.name = :barName" +  
    " AND i.item.itemType = 'B' AND i.item.name = s.item.name" + 
    " GROUP by i.item.name ORDER BY sum(i.quantity) DESC"
    )
    public Page<List<Object[]>> mostPopularBeersPerBar(Pageable pageable, String barName);

    @Query(value = "select s2.manf, sum(i.quantity) " +
    "from Includes i JOIN i.transaction.bar.itemsSold s JOIN treat(i.item as Beer) s2 " +
    "where i.item.name = s.item.name " + 
    "and i.transaction.bar.name = :barName and i.item.itemType = 'B' GROUP by s2.manf ORDER BY sum(i.quantity) DESC")
    public Page<List<Object[]>> mostPopularManfsPerBar(Pageable pageable, String barName);

    @Query(value = "SELECT sum(t.amountPaid)" +
    " FROM Transaction t" +
    " WHERE t.bar.name = :barName"
    + " AND t.dateTime BETWEEN :beginDate AND :endDate")
    public Double timeDistSalesPerBarPerWeek(String barName,Date beginDate, Date endDate);
}