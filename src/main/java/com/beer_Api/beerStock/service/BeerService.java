package com.beer_Api.beerStock.service;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beer_Api.beerStock.dto.BeerDTO;
import com.beer_Api.beerStock.entity.Beer;
import com.beer_Api.beerStock.exception.BeerAlreadyRegisteredException;
import com.beer_Api.beerStock.exception.BeerNotFoundException;
import com.beer_Api.beerStock.exception.BeerStockExceededException;
import com.beer_Api.beerStock.mapper.BeerMapper;
import com.beer_Api.beerStock.repository.BeerRepository;

import lombok.AllArgsConstructor;

@Service                                            //Indica que o Spring faz todo o controle de class.
@AllArgsConstructor(onConstructor = @__(@Autowired))//Vantagem do lombok, fazendo com que Autowired crie em tempo de código sozinho. Facilita manutenção.
public class BeerService {

	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper = BeerMapper.INSTANCE;
	
	public BeerDTO createBeer(BeerDTO beerDTO) throws BeerAlreadyRegisteredException { //metodo para criar.
		verifyIfIsAlreadyRegistered(beerDTO.getName());
		Beer beer = beerMapper.toModel(beerDTO);
		Beer savedBeer = beerRepository.save(beer);
		return beerMapper.toDTO(savedBeer);
	}
	
	public BeerDTO findByName(String name) throws BeerNotFoundException{ //metodo busca por nome.
		Beer foundBeer = beerRepository.findByName(name)
				.orElseThrow(() -> new BeerNotFoundException(name));
		return beerMapper.toDTO(foundBeer);
	}
	
	public List<BeerDTO> listAll(){      //metodo por listagem.
		return beerRepository.findAll()
				.stream()
				.map(beerMapper::toDTO)
				.collect(Collectors.toList());
	}
	
	public void deletebyId(Long id) throws BeerNotFoundException{   //metodo deletar.
		verifyIfExists(id);
		beerRepository.deleteById(id);
	}

	private void verifyIfIsAlreadyRegistered(String name) throws BeerAlreadyRegisteredException {
		Optional<Beer> optSavedBeer = beerRepository.findByName(name);
		if (optSavedBeer.isPresent()) {
			throw new BeerAlreadyRegisteredException(name);
		}
	}
	
	private Beer verifyIfExists(Long id) throws BeerNotFoundException {   //metodo auxiliar.
		return beerRepository.findById(id)
				.orElseThrow(() -> new BeerNotFoundException(id));
	}
	
	public BeerDTO increment(Long id, int quantityToIncrement) throws BeerNotFoundException, BeerStockExceededException{  //metodo auxiliar.
		Beer beerToIncrementStock = verifyIfExists(id);
		int quantityAfterIncrement = quantityToIncrement + beerToIncrementStock.getQuantity();
		if (quantityAfterIncrement <= beerToIncrementStock.getMax()) {
			beerToIncrementStock.setQuantity(beerToIncrementStock.getQuantity() + quantityToIncrement);
			Beer incrementedBeerStock = beerRepository.save(beerToIncrementStock);
			return beerMapper.toDTO(incrementedBeerStock);
		}
		throw new BeerStockExceededException(id, quantityToIncrement);
	}
	
	
}
