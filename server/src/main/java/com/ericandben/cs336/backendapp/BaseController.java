package com.ericandben.cs336.backendapp;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;

public abstract class BaseController<T, K extends Serializable> {

    // PagingAndSortingRepository<T, K> repository;

    @RequestMapping(path="fetchall")
    public @ResponseBody Page<T> fetchAll(Pageable pageable) {
        if (getRepository() == null) return null;
        return getRepository().findAll(pageable);
    }


    // subclasses will implement this method to return their particular repository instance
    public abstract PagingAndSortingRepository<T, K> getRepository();


}