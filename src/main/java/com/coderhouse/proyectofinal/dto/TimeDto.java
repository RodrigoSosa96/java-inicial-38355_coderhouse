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
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
//    @JsonDeserialize(using = MyCustomDeserializer.class)
//    @JsonSerialize(using = MyCustomSerializer.class)
//    @JsonSerialize(using = DateTimeSerializer.class)
    private Date currentDateTime;
//    private String timeZoneName;
//    private String utcOffset;
//    private Long currentFileTime;




}
