package com.ericandben.cs336.backendapp.likes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface LikesRepository extends PagingAndSortingRepository<Likes, LikesKey> {

}