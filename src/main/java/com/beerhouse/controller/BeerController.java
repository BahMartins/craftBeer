package com.beerhouse.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.beerhouse.entity.Beer;
import com.beerhouse.service.BeerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/beers")
@Api(value = "Beer Controller", tags = {"Beer Controller"})
public class BeerController {
	
	@Autowired
	private BeerService beerService;
	
	
	@PostMapping
	@ApiOperation(value = "Create a Beer")
	public ResponseEntity<Beer> save(@Valid @RequestBody Beer beer) {
		return beerService.save(beer);
	}
	
	
	@GetMapping
	@ApiOperation(value = "Return a list of Beers")
	public Page<Beer> findAllBeers(Pageable pageable) {
		return beerService.findAllBeers(pageable);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(value = "Return a Beer by ID")
	public ResponseEntity<Beer> findBeer(@PathVariable(value = "id") Integer id){
		return beerService.findBeerById(id);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Change all data of a Beer by ID")
	public ResponseEntity<Beer> updateBeer(@PathVariable(value = "id") Integer id, @Valid @RequestBody Beer updatedBeer) {
		return beerService.updateBeer(id, updatedBeer);
	}
	
	@PatchMapping("/{id}")
	@ApiOperation(value = "Change one or more data of a Beer by ID")
	public ResponseEntity<Beer> updateBeerData(@PathVariable(value = "id") Integer id, @RequestBody Beer updateBeerData) {
		return beerService.updateBeerData(id, updateBeerData);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(value = "Delete a Beer by ID")
	public ResponseEntity<?> deleteBeer(@PathVariable(value = "id") Integer id) {
		return beerService.deleteBeer(id);
	}
}
