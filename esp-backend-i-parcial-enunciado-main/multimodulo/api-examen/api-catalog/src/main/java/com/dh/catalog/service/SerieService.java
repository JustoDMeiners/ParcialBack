package com.dh.catalog.service;


import com.dh.catalog.model.serie.Serie;
import com.dh.catalog.repository.SerieRepository;
import org.springframework.stereotype.Service;

@Service
public class SerieService {

    private final SerieRepository serieRepository;

    public SerieService(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    public Serie save(Serie serie) {


        return serieRepository.save(serie);
    }
}
