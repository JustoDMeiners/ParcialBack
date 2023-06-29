package com.dh.catalog.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "backendExchange";
    public static final String TOPIC_SERIE_CREATE = "serie.create.congratulation";

    public static final String TOPIC_MOVIE_CREATE = "movie.create.congratulation";


    public static final String QUEUE_MOVIE_CREATE ="queueMovieCreateOK";
    public static final String QUEUE_SERIE_CREATE ="queueSerieCreateOK";

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue queueMovieCreateOK() {
        return new Queue(QUEUE_MOVIE_CREATE);
    }

    @Bean
    public Queue queueSerieCreateOK() {
        return new Queue(QUEUE_SERIE_CREATE);
    }


    @Bean
    public Binding declareBindingSpecificMovie(){
        return BindingBuilder.bind(queueMovieCreateOK()).to(appExchange()).with(TOPIC_MOVIE_CREATE);
    }

    @Bean
    public Binding declareBindingSpecificSerie(){
        return BindingBuilder.bind(queueSerieCreateOK()).to(appExchange()).with(TOPIC_SERIE_CREATE);
    }
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }



    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}
