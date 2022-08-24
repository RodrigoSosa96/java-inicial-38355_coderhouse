package com.coderhouse.proyectofinal.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeDto {
//    2022-08-23T18:42Z
//    @JsonSerialize(using = DateTimeSerializer.class)
    private Date currentDateTime;




}
