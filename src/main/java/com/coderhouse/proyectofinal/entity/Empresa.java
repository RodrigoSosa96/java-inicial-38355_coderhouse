package com.coderhouse.proyectofinal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity(name = "empresa")
@Table(name = "empresa")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id")
    @JsonIgnore
    private Long id;

    @Column(name= "nombre")
    private String nombre;

    @Column(name = "rubro")
    private String rubro;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinTable(name = "empresa_clientes",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id")
    )
    @JsonIgnore
    private Set<Cliente> clientes = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    @JoinTable(name = "empresa_productos",
            joinColumns = @JoinColumn(name = "empresa_id"),
            inverseJoinColumns = @JoinColumn(name = "producto_id")
    )
    @JsonIgnore
    private Set<Producto> productos = new HashSet<>();

}
