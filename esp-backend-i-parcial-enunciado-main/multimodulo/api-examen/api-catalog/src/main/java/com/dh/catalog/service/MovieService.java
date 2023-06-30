package com.dh.catalog.service;


import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.MovieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieServiceClient movieServiceClient;

    @Autowired
    public MovieService(MovieRepository movieRepository, MovieServiceClient movieServiceClient) {
        this.movieRepository = movieRepository;
        this.movieServiceClient = movieServiceClient;
    }

    public void registerMovie (MovieServiceClient.MovieDto movieDto){
        log.info("registerSerie");
        movieRepository.save(
                Movie.builder()
                        .name(movieDto.getName())
                        .genre(movieDto.getGenre())
                        .build()
        );
    }

    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }



}
