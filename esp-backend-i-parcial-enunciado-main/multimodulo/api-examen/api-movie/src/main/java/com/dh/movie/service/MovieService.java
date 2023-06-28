package com.dh.movie.service;



import com.dh.movie.event.MovieCreateProducer;
import com.dh.movie.model.Movie;
import com.dh.movie.repository.MovieRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieCreateProducer movieCreateProducer;
    private final MovieRepository movieRepository;






    public MovieService(MovieCreateProducer movieCreateProducer, MovieRepository movieRepository) {
        this.movieCreateProducer = movieCreateProducer;
        this.movieRepository = movieRepository;

    }


    public List<Movie> findByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    public Movie save(Movie movie) {
        movieCreateProducer.publishCrearMovie(new MovieCreateProducer.Data(movie.getName(), movie.getGenre()));


        return movieRepository.save(movie);
    }


}
