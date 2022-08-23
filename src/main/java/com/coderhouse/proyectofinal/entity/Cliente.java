package com.coderhouse.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

@Entity(name = "cliente")
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cliente_id")
    @JsonIgnore
    private Long id;

    @Column(name= "nombre")
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "apellido")
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Column(name= "dni", unique = true)
    @NotBlank(message = "El dni es obligatorio")
    private String dni;

    @Column (name = "direccion")
    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;

    @Column(name= "fecha_nacimiento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = "America/Argentina/Buenos_Aires")
    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    private LocalDate fechaNacimiento;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Factura> facturas;


}
