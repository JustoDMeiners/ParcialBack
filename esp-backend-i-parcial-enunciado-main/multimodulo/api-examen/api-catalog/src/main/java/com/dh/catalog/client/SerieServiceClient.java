package com.dh.catalog.client;


import com.dh.catalog.model.serie.Serie;
import lombok.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="api-series")
public interface SerieServiceClient {

    @PostMapping("/series")
    void createSerie(@RequestBody SerieDto serie);

    @GetMapping("/api/v1/series/{genre}")
    List<SerieDto> getSerieByGenre();



    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    class SerieDto{
        private String id;

        private String name;

        private String genre;

    }
}
