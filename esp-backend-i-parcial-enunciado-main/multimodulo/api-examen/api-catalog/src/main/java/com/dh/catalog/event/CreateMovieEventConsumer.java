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
    public void listen(CreateMovieEventConsumer.Data message){
        System.out.print("NOMBRE DE PELICULA "+ message.getName());
        //procesamiento
        Movie movie = new Movie();
        movie.setName(message.getName());
        movie.setGenre((message.getGenre()));
        movieService.save(movie);
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
