package com.ericandben.cs336.backendapp.includes;
import java.util.List;

import com.ericandben.cs336.backendapp.transaction.Transaction;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface IncludesRepository extends PagingAndSortingRepository<Includes, IncludesKey> {

    //public Includes findByPkey(IncludesKey key);

    @Query(value = 
    "SELECT i FROM Includes i ")
    public List<Includes> getTuplesByTid(Pageable pageable);

    @Query(value = "SELECT t FROM Transaction t")
    public List<Transaction> getTransactions(Pageable pageable);

}