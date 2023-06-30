package com.dh.catalog.service;



import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.model.serie.Serie;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CatalogService {

    private final MovieServiceClient movieServiceFeignClient;
    private final SerieServiceClient serieServiceFeignClient;

    private final SerieService serieService;
    private final MovieService movieService;





    @Autowired
    public CatalogService(MovieServiceClient movieServiceFeignClient, SerieServiceClient serieServiceFeignClient, SerieService serieService, MovieService movieService) {
        this.movieServiceFeignClient = movieServiceFeignClient;
        this.serieServiceFeignClient = serieServiceFeignClient;

        this.serieService = serieService;
        this.movieService = movieService;
    }


    @Retry(name = "retryMoviesSearch")
    @CircuitBreaker(name = "moviesFindBy", fallbackMethod = "fallbackCatalogMovies")
    public List<MovieServiceClient.MovieDto> getMoviesByGenre(String genre) {
        return movieServiceFeignClient.getMovieByGenre();
    }
    @Retry(name = "retrySeriesSearch")
    @CircuitBreaker(name = "seriesFindBy", fallbackMethod = "fallbackCatalogSeries")
    public List<SerieServiceClient.SerieDto> getSeriesByGenre(String genre) {
        return serieServiceFeignClient.getSerieByGenre();
    }









    public void fallbackCatalogMovies(Exception e) {
        // Obtener las películas desde la base de datos MongoDB como fallback
        List<MovieServiceClient.MovieDto> movies = movieServiceFeignClient.getMovieByGenre();


    }

    public void fallbackCatalogSeries(Exception e) {

        List<SerieServiceClient.SerieDto> series = serieServiceFeignClient.getSerieByGenre();


    }




}
