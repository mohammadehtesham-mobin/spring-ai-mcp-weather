package com.nagp.mcpserver.weather.model.record;

public record GeoCoordinateResponse(
        String lat,
        String lon,
        String display_name
) {}
