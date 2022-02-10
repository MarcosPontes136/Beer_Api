package com.beer_Api.beerStock.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.beer_Api.beerStock.dto.BeerDTO;
import com.beer_Api.beerStock.entity.Beer;

@Mapper
public interface BeerMapper {

	BeerMapper INSTANCE = Mappers.getMapper(BeerMapper.class);
	
	Beer toModel(BeerDTO beerDTO);
	
	BeerDTO toDTO(Beer beer);
}
