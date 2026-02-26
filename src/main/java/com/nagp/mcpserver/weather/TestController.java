package com.nagp.mcpserver.weather;

import com.nagp.mcpserver.weather.model.record.GeoCoordinateResponse;
import com.nagp.mcpserver.weather.model.record.NwsForecastResponse;
import com.nagp.mcpserver.weather.service.GeoCoordinateServices;
import com.nagp.mcpserver.weather.service.NwsWeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/weather")
public class TestController {

    private final GeoCoordinateServices geoCoordsServices;
    private final NwsWeatherService nwsWeatherService;

    @GetMapping("/coord")
    public GeoCoordinateResponse getCordinates(@RequestParam String city) {
        return geoCoordsServices.getCoordinates(city);
    }

    @GetMapping("/info")
    public List<NwsForecastResponse.Period> getWeather(@RequestParam double lat, @RequestParam double lan) {
        return nwsWeatherService.getForecast(lat, lan);
    }

}
