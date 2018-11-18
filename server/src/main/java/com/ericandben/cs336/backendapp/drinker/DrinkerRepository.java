package com.ericandben.cs336.backendapp.drinker;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import com.ericandben.cs336.backendapp.includes.*;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DrinkerRepository extends PagingAndSortingRepository<Drinker, String>, DrinkerRepositoryCustom {
    public Drinker findByName(String name);

    /*
    select sum(i.quantity) as total, i.item from Includes i inner join Transactions t
    on i.bar = t.bar and i.tid = t.tid
    where t.drinker = 'Aaron Endo'
    and i.item in (select name from Beers)
    group by i.item
    order by total desc
    limit 5;
*/




    /*
    @Query(value = 
    "SELECT i FROM Includes i " + 
    "WHERE i.pkey.transaction.drinker.name = :drinkerName")

    */

    /*
    @Query(value = 
    "SELECT i FROM Includes i JOIN i.pkey.transaction t " + 
    "WHERE t.drinker.name = :drinkerName")

    */
    @Query(value = "SELECT i.item.name, sum(i.quantity) " +
    "FROM Includes i " + 
    "WHERE i.item.itemType = 'B' AND i.transaction.drinker.name = :drinkerName " +
    "Group by i.item.name " +
    "Order by sum(i.quantity) DESC")
    public Page<List<Object[]>> topBeersForDrinker(Pageable pageable, String drinkerName);
    
    @Query(value = "SELECT t.tid, t.bar.name, t.amountPaid, t.dateTime, t.tip " +
    "FROM Transaction t " + 
    "WHERE t.drinker.name = :drinkerName")
    public List<Object[]> allTransactionsForDrinker(String drinkerName);

}