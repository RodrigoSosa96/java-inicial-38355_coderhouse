package com.coderhouse.proyectofinal.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


/**
 * {"$id":"1","currentDateTime":"2022-08-16T22:22Z","utcOffset":"00:00:00","isDayLightSavingsTime":false,"dayOfTheWeek":"Tuesday","timeZoneName":"UTC","currentFileTime":133051621545765162,"ordinalDate":"2022-228","serviceResponse":null}
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeDto {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private Date currentDateTime;
    private String utcOffset;
    private Boolean isDayLightSavingsTime;
    private String dayOfTheWeek;
    private String timeZoneName;
    private Long currentFileTime;
    private String ordinalDate;
    private String serviceResponse;
}
