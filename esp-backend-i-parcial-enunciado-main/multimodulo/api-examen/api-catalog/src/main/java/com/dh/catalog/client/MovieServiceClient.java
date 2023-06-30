package com.dh.catalog.client;

import com.dh.catalog.model.movie.Movie;

import lombok.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name="api-movie")
public interface MovieServiceClient {

    @PostMapping("/movies")
    void createMovie(@RequestBody MovieDto movie);

	@GetMapping("/api/v1/movies/{genre}")
	List<MovieDto> getMovieByGenre();



	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	class MovieDto{


		private String name;

		private String genre;




	}

}
