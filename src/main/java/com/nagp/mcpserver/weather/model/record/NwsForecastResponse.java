package com.nagp.mcpserver.weather.model.record;

import java.util.List;

public record NwsForecastResponse(Properties properties) {
    public record Properties(List<Period> periods) {}
    public record Period(
            String name,
            int temperature,
            String temperatureUnit,
            String windSpeed,
            String shortForecast,
            String detailedForecast
    ) {}
}
