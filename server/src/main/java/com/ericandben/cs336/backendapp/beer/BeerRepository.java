package com.ericandben.cs336.backendapp.beer;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
/*class BarBundle {
    String bar;
    int amountSold;

    public BarBundle(String bar, int amountSold) {
        this.bar = bar;
        this.amountSold = amountSold;
    }
}*/
public interface BeerRepository extends PagingAndSortingRepository<Beer, String> {
    public Beer findByName(String name);
    @Query(value = "select i.transaction.bar.name, sum(i.quantity) from Includes i" + 
    " where i.item.name = :beername" + 
    " group by i.transaction.bar.name" + 
    " order by sum(i.quantity) desc")
    public Page<List<Object[]>> topFiveBars(Pageable pageable, String beername);

    @Query(value = "select i.transaction.drinker.name, sum(i.quantity) from Includes i " +
    "where i.item.name = :beername " +
    "group by i.transaction.drinker.name " +
    "order by sum(i.quantity) desc")
    public Page<List<Object[]>> topFiveDrinkers(Pageable pageable, String beername);
}