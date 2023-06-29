package com.dh.apiseries.service;



import com.dh.apiseries.event.SerieCreateProducer;
import com.dh.apiseries.model.Serie;
import com.dh.apiseries.repository.SerieRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SerieService {

    private final SerieRepository serieRepository;

    private final SerieCreateProducer serieCreateProducer;



    public SerieService(SerieRepository serieRepository, SerieCreateProducer serieCreateProducer) {
        this.serieRepository = serieRepository;

        this.serieCreateProducer = serieCreateProducer;
    }





    public List<Serie> getSeriesByGenre(String genre) {
        return serieRepository.findAllByGenre(genre);
    }

    /*public Serie createSerie(Serie serieDto) {
        return repository.save(serieDto);
    }*/
    public Serie createSerie(Serie serie) {
        serieCreateProducer.publishCrearSerie(new SerieCreateProducer.Data(serie.getName(), serie.getGenre()));

        return serieRepository.save(serie);
    }


}
