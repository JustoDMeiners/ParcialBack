package com.dh.catalog.service;


import com.dh.catalog.client.SerieServiceClient;
import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.SerieRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SerieService {

    private final SerieRepository serieRepository;
    private final SerieServiceClient serieServiceFeignClient;



    public SerieService(SerieRepository serieRepository, SerieServiceClient serieServiceFeignClient1) {
        this.serieRepository = serieRepository;

        this.serieServiceFeignClient = serieServiceFeignClient1;
    }

    public void registerSerie (SerieServiceClient.SerieDto serieDto){
        log.info("registerSerie");
        serieRepository.save(
                Serie.builder()
                        .id(Long.parseLong(
                                serieDto.getId()
                                        .replaceAll("[^0-9]", "")
                                        .substring(0, Math.min(10, serieDto.getId().length()))))
                        .name(serieDto.getName())
                        .genre(serieDto.getGenre())
                        .build()
        );
    }
}
