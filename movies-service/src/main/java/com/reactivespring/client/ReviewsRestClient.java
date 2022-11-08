package com.reactivespring.client;

import com.reactivespring.domain.MovieInfo;
import com.reactivespring.domain.Review;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;

@Component
public class ReviewsRestClient {

    private WebClient webClient;

    @Value("${restClient.reviewsUrl}")
    private String reviewsoUrl;

    public ReviewsRestClient(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<Review> retreveReviews(String movieId){
        var url = UriComponentsBuilder.fromHttpUrl(reviewsoUrl)
                .queryParam("movieInfoId", movieId)
                .buildAndExpand().toUriString();
        return webClient
                .get()
                .uri(url)
                .retrieve()
                .bodyToFlux(Review.class);

    }

}
