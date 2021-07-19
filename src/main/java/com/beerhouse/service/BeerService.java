package com.beerhouse.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.beerhouse.entity.Beer;

public interface BeerService {

	ResponseEntity<Beer> save(Beer beer);

	Page<Beer> findAllBeers(Pageable pageable);

	ResponseEntity<Beer> findBeerById(Integer id);

	ResponseEntity<Beer> updateBeer(Integer id, @Valid Beer updatedBeer);

	ResponseEntity<Beer> updateBeerData(Integer id, Beer updateBeerData);

	ResponseEntity<?>  deleteBeer(Integer id);

}
