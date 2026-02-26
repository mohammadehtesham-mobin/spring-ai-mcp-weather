package com.nagp.mcpserver.weather.model.record;

public record NwsPointResponse(Properties properties) {
    public record Properties(String forecast) {}
}
