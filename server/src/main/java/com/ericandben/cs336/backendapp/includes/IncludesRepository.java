package com.ericandben.cs336.backendapp.includes;

import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface IncludesRepository extends CrudRepository<Includes, IncludesKey> {

}