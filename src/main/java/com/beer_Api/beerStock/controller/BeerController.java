package com.beer_Api.beerStock.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.beer_Api.beerStock.dto.BeerDTO;
import com.beer_Api.beerStock.dto.QuantityDTO;
import com.beer_Api.beerStock.exception.BeerAlreadyRegisteredException;
import com.beer_Api.beerStock.exception.BeerNotFoundException;
import com.beer_Api.beerStock.exception.BeerStockExceededException;
import com.beer_Api.beerStock.service.BeerService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/beers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BeerController {
	
	private final BeerService beerService;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public BeerDTO createBeer(@RequestBody @Valid BeerDTO beerDTO) throws BeerAlreadyRegisteredException { //metodo de criar
		return beerService.createBeer(beerDTO);
	}
	
	public BeerDTO findbyName(@PathVariable String name) throws BeerNotFoundException{ //metodo pesquisar nome.
		return beerService.findByName(name);
	}
	
	@GetMapping
	public List<BeerDTO> listBeer(){   //metodo listagem.
		return beerService.listAll();
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) throws BeerNotFoundException{  //metodo de deletar.
		beerService.deletebyId(id);
	}
	
	public BeerDTO increment(@PathVariable Long id, @RequestBody @Valid QuantityDTO quantityDTO) throws BeerNotFoundException, BeerStockExceededException{
		return beerService.increment(id, quantityDTO.getQuantity());
	}
}
