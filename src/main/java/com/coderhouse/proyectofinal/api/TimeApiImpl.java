package com.coderhouse.proyectofinal.api;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TimeApiImpl implements TimeApi {

    RestTemplate restTemplate = new RestTemplate();

    public String getTime() {
        final String url = "http://worldclockapi.com/api/json/utc/now";
        return restTemplate.getForObject(url, String.class);
    }
}
