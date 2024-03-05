package com.epam.advanced.java.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "file:./docker/postgres.properties")
public class DataSourceConfig {
}
