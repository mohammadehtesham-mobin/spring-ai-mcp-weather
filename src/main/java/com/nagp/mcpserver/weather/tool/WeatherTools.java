package com.nagp.mcpserver.weather.tool;

import com.nagp.mcpserver.weather.model.record.GeoCoordinateResponse;
import com.nagp.mcpserver.weather.model.record.NwsForecastResponse;
import com.nagp.mcpserver.weather.service.GeoCoordinateServices;
import com.nagp.mcpserver.weather.service.NwsWeatherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WeatherTools {
    private static final Logger logger = LoggerFactory.getLogger(WeatherTools.class);

    private final GeoCoordinateServices geoCoordsServices;
    private final NwsWeatherService nwsWeatherService;


    @Tool(name = "getWeatherDetailsForCity", description = "get the weather details of a city")
    public String getWeatherDetailsForCity(@ToolParam(description = "name of the city") String city) {
        logger.info("Fetching weather details for city: {}", city);

        GeoCoordinateResponse cityCoords;


        try {
            // get city coordinates
             cityCoords = geoCoordsServices.getCoordinates(city);


            List<NwsForecastResponse.Period> forecast = nwsWeatherService.getForecast(Double.parseDouble(cityCoords.lat())
                    , Double.parseDouble(cityCoords.lon()));
            String weatherInfo = formatForecast(city, forecast);
            logger.info(weatherInfo);
            return weatherInfo;
        }
        catch (Exception e) {
            logger.error("City or weather details not found");
            return "Error: Could not retrieve weather for '" + city + "'. " +
                    "Note that this service only supports locations within the United States.";
        }

    }

    private String formatForecast(String city, List<NwsForecastResponse.Period> periods) {
        StringBuilder sb = new StringBuilder("Weather forecast for " + city + ":\n");
        for (int i = 0; i < Math.min(periods.size(), 3); i++) { // Top 3 periods (Today, Tonight, Tomorrow)
            NwsForecastResponse.Period p = periods.get(i);
            sb.append(String.format("- %s: %d%s. %s%n %s%n",
                    p.name(), p.temperature(), p.temperatureUnit(), p.shortForecast(), p.detailedForecast()));
        }
        return sb.toString();
    }
}
