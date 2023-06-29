package com.dh.catalog.service;


import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.repository.MovieRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie save(Movie movie) {


        return movieRepository.save(movie);
    }



}
