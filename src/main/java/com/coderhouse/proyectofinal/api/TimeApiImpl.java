package com.coderhouse.proyectofinal.api;

import com.coderhouse.proyectofinal.dto.TimeDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class TimeApiImpl implements TimeApi {

    RestTemplate restTemplate = new RestTemplate();

    public Optional<Date> getTime() {
        final String url = "http://worldclockapi.com/api/json/utc/now";
        try {
            TimeDto time = restTemplate.getForObject(url, TimeDto.class, "currentDateTime");
            if(time != null && time.getCurrentDateTime() != null) {
                return Optional.of(time.getCurrentDateTime());
            }
            return Optional.empty();
        } catch (NullPointerException e) {
            log.error("Error al obtener la fecha actual", e);
            return Optional.empty();
        }

    }
}
