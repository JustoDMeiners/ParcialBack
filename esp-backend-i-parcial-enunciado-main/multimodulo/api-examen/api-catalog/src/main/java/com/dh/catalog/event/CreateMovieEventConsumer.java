package com.dh.catalog.event;


import com.dh.catalog.client.MovieServiceClient;
import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.movie.Movie;
import com.dh.catalog.service.MovieService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CreateMovieEventConsumer {

    private final MovieService movieService;
    private final MovieServiceClient movieServiceClient;


    public CreateMovieEventConsumer(MovieService movieService, MovieServiceClient movieServiceClient) {
        this.movieService = movieService;
        this.movieServiceClient = movieServiceClient;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_MOVIE_CREATE)
    public void listen(Movie message){
        System.out.print("NOMBRE DE PELICULA "+ message.getName());
        //procesamiento
        movieService.registerMovie(MovieServiceClient.MovieDto.builder().name(message.getName()).genre(message.getGenre()).build());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class Data{
        private String name;

        private String genre;

    }
}
