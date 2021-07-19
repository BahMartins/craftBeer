package com.beerhouse.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "beers")
@AllArgsConstructor
@NoArgsConstructor
public class Beer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull @NotBlank
	private String name;
	
	@NotNull @NotBlank
	private String ingredients;
	
	@NotNull @NotBlank
	private String alcoholContent;
	
	@NotNull
    @DecimalMin(value = "0.0")
    @Digits(integer=4, fraction=2)
	private BigDecimal price;
	
	@NotNull @NotBlank
	private String category;

}
