package com.dh.apiseries.controller;


import com.dh.apiseries.model.Serie;
import com.dh.apiseries.service.SerieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/series")
public class SerieController {

    private SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }



    @GetMapping("/{genre}")
    ResponseEntity<List<Serie>> getSerieByGenre(@PathVariable String genre){
        return ResponseEntity.ok(serieService.getSeriesByGenre(genre));
    }

    @PostMapping("/save")
    void createNewSerie(@RequestBody Serie serie) {
        serieService.createSerie(serie);
    }


}
