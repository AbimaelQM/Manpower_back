package br.motorola.manpower.config;

import java.util.Arrays;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class FilterCors {
    
    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration configCors = new CorsConfiguration();
        configCors.setAllowCredentials(true);
        configCors.setAllowedOrigins(Arrays.asList("https://main.d3t7t5bx3s36d0.amplifyapp.com"));
        configCors.setAllowedMethods(Arrays.asList("*"));
        configCors.setAllowedHeaders(Arrays.asList("*"));

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", configCors);

        return new CorsFilter(configSource);

    }

}
