package com.ericandben.cs336.backendapp.transaction;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, TransactionKey> {
    //public List<Transaction> findByPkeyTid(int tid);
}