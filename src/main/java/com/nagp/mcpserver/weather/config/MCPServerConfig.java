package com.nagp.mcpserver.weather.config;

import com.nagp.mcpserver.weather.tool.WeatherTools;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MCPServerConfig {
    @Bean
    public ToolCallbackProvider weatherToolsProvider(WeatherTools weatherTools) {
        return MethodToolCallbackProvider.builder()
                .toolObjects(weatherTools)
                .build();
    }
}
