package com.beer_Api.beerStock.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.beer_Api.beerStock.enums.BeerType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BeerDTO {

	private Long id;
	
	@NotNull
	@Size(min = 1, max = 200)
	private String name;

	@NotNull
	@Size(min = 1, max = 200)
	private String brand;
	
	@NotNull
	@Max(500)
	private int max;
	
	@NotNull
	@Max(100)
	private int quantity;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private BeerType type;
}
