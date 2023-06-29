package com.dh.movie.event;


import com.dh.movie.config.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MovieCreateProducer {

    private final RabbitTemplate rabbitTemplate;

    public MovieCreateProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishCrearMovie(MovieCreateProducer.Data message){
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.TOPIC_MOVIE_CREATE,message);
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
