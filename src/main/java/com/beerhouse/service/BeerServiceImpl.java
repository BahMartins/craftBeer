package com.beerhouse.service;

import java.math.BigDecimal;
import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.beerhouse.entity.Beer;
import com.beerhouse.repository.BeerRepository;

@Service
public class BeerServiceImpl implements BeerService{
	
	@Autowired
	private BeerRepository beerRepository;
	
	@Override
	public ResponseEntity<Beer> save(Beer beer) {
		
		Optional<Beer> beerFound = findBeer(beer.getId());
		if(beerFound.isPresent()) {
			return new ResponseEntity<Beer>(HttpStatus.BAD_REQUEST);
		}
		Beer savedBeer = beerRepository.save(beer);
		URI uri = createUri(savedBeer);
		return ResponseEntity.created(uri).body(beer);
	}

	@Override
	public Page<Beer> findAllBeers(Pageable pageable) {
		return beerRepository.findAll(pageable);
	}

	@Override
	public ResponseEntity<Beer> findBeerById(Integer id) {
		Optional<Beer> beer = findBeer(id);
		if(!beer.isPresent()) {
			return ResponseEntity.notFound().build();
		} 
		return new ResponseEntity<Beer>(beer.get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Beer> updateBeer(Integer id, @Valid Beer updatedBeer) {
		Optional<Beer> beer = findBeer(id);
		
		if(!beer.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		updatedBeer.setId(beer.get().getId());
		Beer savedBeer = beerRepository.save(updatedBeer);
		return new ResponseEntity<Beer>(savedBeer, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Beer> updateBeerData(Integer id, Beer updateBeerData) {
		Optional<Beer> beer = findBeer(id);
		
		if(!beer.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		Beer updatedParcialBeer = updatedPartiallyBeer(beer, updateBeerData);
		Beer savedBeer = beerRepository.save(updatedParcialBeer);
		return new ResponseEntity<Beer>(savedBeer, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> deleteBeer(Integer id) {
		Optional<Beer> beer = findBeer(id);
		
		if(!beer.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		beerRepository.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
	
	private Beer updatedPartiallyBeer(Optional<Beer> beer, Beer updateBeerData) {
		updateBeerData.setId(beer.get().getId());
		
		if(updateBeerData.getName() == null || updateBeerData.getName().isBlank() || updateBeerData.getName().isEmpty()){
			updateBeerData.setName(beer.get().getName());
		}
		if(updateBeerData.getIngredients() == null || updateBeerData.getIngredients().isBlank() || updateBeerData.getIngredients().isEmpty()){
			updateBeerData.setIngredients(beer.get().getIngredients());
		}
		if(updateBeerData.getAlcoholContent() == null || updateBeerData.getAlcoholContent().isBlank() || updateBeerData.getAlcoholContent().isEmpty()){
			updateBeerData.setAlcoholContent(beer.get().getAlcoholContent());
		}
		if(updateBeerData.getPrice() == null || updateBeerData.getPrice().compareTo(BigDecimal.ZERO) <= 0){
			updateBeerData.setPrice(beer.get().getPrice());
		}
		if(updateBeerData.getCategory() == null || updateBeerData.getCategory().isBlank() || updateBeerData.getCategory().isEmpty()){
			updateBeerData.setCategory(beer.get().getCategory());
		}
		return updateBeerData;
	}

	private Optional<Beer> findBeer(Integer id) {
		return beerRepository.findById(id);
	}
	
	private URI createUri(Beer beerCreated) {
		ServletUriComponentsBuilder currentRequest = ServletUriComponentsBuilder.fromCurrentRequest();
		return currentRequest.path("/{id}").buildAndExpand(beerCreated.getId()).toUri();
	}
}
