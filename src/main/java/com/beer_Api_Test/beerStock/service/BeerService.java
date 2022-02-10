package com.beer_Api_Test.beerStock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beer_Api_Test.beerStock.dto.BeerDTO;
import com.beer_Api_Test.beerStock.exception.BeerAlreadyRegisteredException;
import com.beer_Api_Test.beerStock.mapper.BeerMapper;
import com.beer_Api_Test.beerStock.repository.BeerRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerService {

	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper = BeerMapper.INSTANCE;
	
	public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException {
		return beerDTO;
		
	}
}
