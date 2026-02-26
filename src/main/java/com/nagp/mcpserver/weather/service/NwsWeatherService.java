package com.nagp.mcpserver.weather.service;

import com.nagp.mcpserver.weather.model.record.NwsForecastResponse;
import com.nagp.mcpserver.weather.model.record.NwsPointResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

import static com.nagp.mcpserver.weather.constant.AppConstant.API_HEADER_USER_AGENT;

@Service
public class NwsWeatherService {

    private final RestClient restClient;

    public NwsWeatherService(RestClient.Builder builder
            , @Value("${api.nws.url}") String nwsUrl
            , @Value("${app.user-agent}") String userAgent) {
        this.restClient = builder
                .baseUrl(nwsUrl)
                .defaultHeader(API_HEADER_USER_AGENT, userAgent)
                .build();
    }

    public List<NwsForecastResponse.Period> getForecast(double lat, double lon) {
        // Get the metadata for the coordinates
        NwsPointResponse weatherData = restClient.get()
                .uri("/points/{lat},{lon}", lat, lon)
                .retrieve()
                .body(NwsPointResponse.class);

        if (weatherData == null || weatherData.properties() == null) {
            throw new RuntimeException("Could not find NWS grid for these coordinates.");
        }

        // get Forecast for coordinates using forecastUrl
        String forecastUrl = weatherData.properties().forecast();

        NwsForecastResponse forecastData = restClient.get()
                .uri(forecastUrl) // We use the full URL provided by NWS
                .retrieve()
                .body(NwsForecastResponse.class);

        return forecastData.properties().periods();
    }

}
