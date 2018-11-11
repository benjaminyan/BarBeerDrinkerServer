package com.ericandben.cs336.backendapp.drinker;

import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface DrinkerRepository extends CrudRepository<Drinker, Integer>, DrinkerRepositoryCustom {
    public Drinker findByName(String name);
}