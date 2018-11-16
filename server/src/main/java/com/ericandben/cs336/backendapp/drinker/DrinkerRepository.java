package com.ericandben.cs336.backendapp.drinker;
import org.springframework.data.jpa.repository.Query;
import com.ericandben.cs336.backendapp.includes.*;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface DrinkerRepository extends CrudRepository<Drinker, Integer>, DrinkerRepositoryCustom {
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


    @Query(value = 
    "SELECT i FROM Includes i JOIN i.transaction t " + 
    "WHERE t.drinker.name = :drinkerName")
    public List<Includes> topBeers(String drinkerName);

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

}