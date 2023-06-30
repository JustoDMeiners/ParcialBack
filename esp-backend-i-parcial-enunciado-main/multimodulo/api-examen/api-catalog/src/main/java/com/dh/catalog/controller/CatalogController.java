package com.dh.catalog.controller;

import com.dh.catalog.client.MovieServiceClient;

import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalog")
public class CatalogController {

	private final MovieServiceClient movieServiceClient;
	private final SerieServiceClient serieServiceClient;
	private final MovieRepository movieRepository;



	public CatalogController(MovieServiceClient movieServiceClient, SerieServiceClient serieServiceClient, MovieRepository movieRepository) {
		this.movieServiceClient = movieServiceClient;
		this.serieServiceClient = serieServiceClient;
		this.movieRepository = movieRepository;
	}

	@GetMapping("/movies/{genre}")
	ResponseEntity<List<Movie>> getGenre(@PathVariable String genre) {
		return ResponseEntity.ok(movieRepository.findByGenre(genre));
	}




	@GetMapping("/series/{genre}")
	ResponseEntity<List<SerieServiceClient.SerieDto>> getGenreSerie(@PathVariable String genre) {
		return ResponseEntity.ok(serieServiceClient.getSerieByGenre());
	}

}
