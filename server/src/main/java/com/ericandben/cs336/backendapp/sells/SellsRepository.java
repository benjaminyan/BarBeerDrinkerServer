package com.ericandben.cs336.backendapp.sells;


import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.domain.Page;

interface SellsSummary {
    BarSummary getBar();
    ItemSummary getItem();
    Double getPrice();

    interface BarSummary {
        String getName();
    }

    interface ItemSummary {
        String getName();
    }
}
public interface SellsRepository extends PagingAndSortingRepository<Sells, SellsKey> {

    // public Page<SellsSummary> findAllProjectedBy(Pageable pageable);

}