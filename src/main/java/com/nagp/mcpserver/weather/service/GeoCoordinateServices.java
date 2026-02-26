package com.nagp.mcpserver.weather.service;

import com.nagp.mcpserver.weather.model.record.GeoCoordinateResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

import static com.nagp.mcpserver.weather.constant.AppConstant.API_HEADER_USER_AGENT;

@Service
public class GeoCoordinateServices {
    private final RestClient restClient;


    public GeoCoordinateServices(RestClient.Builder builder
            , @Value("${api.nominatim.url}") String nominatimUrl
            , @Value("${app.user-agent}") String userAgent) {
        this.restClient = builder
                .baseUrl(nominatimUrl)
                .defaultHeader(API_HEADER_USER_AGENT, userAgent)
                .build();
    }

    public GeoCoordinateResponse getCoordinates(String city) {
        List<GeoCoordinateResponse> results = restClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/search")
                        .queryParam("q", city)
                        .queryParam("format", "json")
                        .queryParam("limit", "1")
                        .build())
                .retrieve()
                .body(new ParameterizedTypeReference<List<GeoCoordinateResponse>>() {});

        if (results == null || results.isEmpty()) {
            throw new RuntimeException("City not found: " + city);
        }

        return results.get(0);
    }
}
