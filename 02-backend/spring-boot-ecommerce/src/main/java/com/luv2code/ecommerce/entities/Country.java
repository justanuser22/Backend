package com.luv2code.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "countries")
@Getter
@Setter
public class Country {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    // Relación JPA -> OneToMany, ya que 1 país tiene muchos estados, provincias, etc.
    @OneToMany(mappedBy = "country")
    @JsonIgnore // ignraremos el JSON de los 'states'
    private List<State> states;
}
