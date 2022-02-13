package com.beer_Api.beerStock.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.beer_Api.beerStock.entity.Beer;

public interface BeerRepository extends JpaRepository<Beer, Long> { // class responsavel em conectar com o banco de
																	// dados + o JpaRepository, resumo:CRUD.

	Optional<Beer> findByName(String name); // Optional é um meio de validações.
}
