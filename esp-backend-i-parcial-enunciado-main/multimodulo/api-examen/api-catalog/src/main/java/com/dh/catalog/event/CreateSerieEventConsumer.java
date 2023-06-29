package com.dh.catalog.event;


import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.config.RabbitMQConfig;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.service.SerieService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class CreateSerieEventConsumer {

    private final SerieService serieService;
    private final SerieServiceClient serieServiceClient;

    public CreateSerieEventConsumer(SerieService serieService, SerieServiceClient serieServiceClient) {
        this.serieService = serieService;
        this.serieServiceClient = serieServiceClient;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_SERIE_CREATE)
    public void listen(CreateSerieEventConsumer.Data message){
        System.out.print("NOMBRE DE SERIE"+ message.getName());
        //procesamiento

        Serie serie = new Serie();
        serie.setName(message.getName());
        serie.setGenre((message.getGenre()));
        serieService.save(serie);


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
