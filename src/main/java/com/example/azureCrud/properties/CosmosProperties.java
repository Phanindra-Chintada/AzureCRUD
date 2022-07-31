package com.example.azureCrud.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "cosmos")
public class CosmosProperties {
    private String uri;
    private String key;//primary key or authentication key for the azure cosmos service
    private String database;
    private String container;
}
