package com.luv2code.ecommerce.dao;

import com.luv2code.ecommerce.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("http://localhost:4200")
@RepositoryRestResource // Endpoint-> 'http://localhost:8080/api/states/search/findByCountryCode?code=IN, etc'
public interface StateRepository extends JpaRepository<State, Integer> {

    // Querie para encontrar los estados dependiendo del código del país.
    List<State> findByCountryCode(@Param("code") String code);
}
