package com.coderhouse.proyectofinal.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "factura")
@Table(name = "factura")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"numero"})
@JsonIgnoreProperties({"hibernateLazyInitializer", "referenceList"})
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "factura_id")
    @JsonIgnore
    private Long id;

    @Column(name = "fecha")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "America/Argentina/Buenos_Aires")
    private LocalDateTime fecha;

    @Column(name = "tipo_factura")
    private char tipoFactura = 'A';

    @Column(name = "total_factura")
    private Double totalFactura;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "factura", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<DetalleFactura> detallesFacturas;


    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

/*  formatea el número de factura
    Por anécdota traté usando @JsonSerialize y @Convert clases, funcionaban, pero solo al pedir datos
*/
    @JsonGetter("numero")
    public String getLeadingNumber() {
        return String.format("%06d", id);
    }




}
