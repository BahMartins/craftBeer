package com.beerhouse.controllerTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.beerhouse.entity.Beer;
import com.beerhouse.repository.BeerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class BeerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BeerRepository beerRepository;
	
	@BeforeEach
	public void setup() {
		Beer beer = new Beer(1, "Test Mock2", "Test Mock2", "Test Mock2", new BigDecimal(0.0), "Test Mock2");
		Optional<Beer> beersOptional = Optional.ofNullable(beer);
		BDDMockito.when(beerRepository.findById(1)).thenReturn(beersOptional);
	}

	@Test
	public void shouldListAllBeers() throws Exception {
		List<Beer> beers = mockDataBase();
		BDDMockito.when(beerRepository.findAll()).thenReturn(beers);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/beers"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void shouldNotListAllBeers() throws Exception {
		List<Beer> beers = new ArrayList<>();
		BDDMockito.when(beerRepository.findAll()).thenReturn(beers);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/beers"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void ShoulFindBeerById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/beers/{id}", 1))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void ShoulNotFindBeerById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/beers/{id}", 2))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}

	@Test
	public void shouldSaveBeer() throws Exception {
		Beer beer = new Beer(1, "Test Mock3", "Test Mock3", "Test Mock3", new BigDecimal(0.0), "Test Mock3");
		BDDMockito.when(beerRepository.save(beer)).thenReturn(beer);

		mockMvc.perform(MockMvcRequestBuilders.post("/beers")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(beer)))
				.andExpect(MockMvcResultMatchers.status().isCreated());
	}
	
	@Test
	public void shouldNotSaveBeer() throws Exception {
		Beer beer = new Beer(3, null, "Test Mock3", "Test Mock3", new BigDecimal(0.0), "Test Mock3");
		BDDMockito.when(beerRepository.save(beer)).thenReturn(beer);

		mockMvc.perform(MockMvcRequestBuilders.post("/beers")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(beer)))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void shouldDeleteBeerById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/beers/{id}", 1))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void shouldNotDeleteBeerById() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/beers/{id}", 2))
			.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
	
	@Test
	public void shouldUpdateBeerWithAllData() throws Exception {
		Beer updatedBeer = new Beer(1, "Test Update", "Test Update", "Test Update", new BigDecimal(0.0), "Test Update");
		BDDMockito.when(beerRepository.save(updatedBeer)).thenReturn(updatedBeer);

		mockMvc.perform(MockMvcRequestBuilders.put("/beers/{id}", 1)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(updatedBeer)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void shouldUpdateBeerWithSomeData() throws Exception {
		Beer updatedBeer = new Beer(1, "Test Update", "Test Update", "Test Update", new BigDecimal(0.0), "Test Update");
		BDDMockito.when(beerRepository.save(updatedBeer)).thenReturn(updatedBeer);

		mockMvc.perform(MockMvcRequestBuilders.patch("/beers/{id}", 1)
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(updatedBeer)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	private List<Beer> mockDataBase() {
		Beer beer = new Beer(1, "Test Mock2", "Test Mock2", "Test Mock2", new BigDecimal(0.0), "Test Mock2");
		Beer beer2 = new Beer(2, "Test Mock2", "Test Mock2", "Test Mock2", new BigDecimal(0.0), "Test Mock2");
		List<Beer> beers = new ArrayList<Beer>();
		beers.add(beer);
		beers.add(beer2);
		return beers;
	}
}
