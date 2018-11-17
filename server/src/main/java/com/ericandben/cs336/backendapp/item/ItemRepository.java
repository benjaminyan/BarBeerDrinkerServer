package com.ericandben.cs336.backendapp.item;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ItemRepository extends PagingAndSortingRepository<Item, String> {
    public Item findByName(String name);
}