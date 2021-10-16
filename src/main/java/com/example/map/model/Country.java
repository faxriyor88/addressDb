package com.example.map.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String countryname;
    @ManyToOne(optional = false)
    private Continent continent;

    public Country(String countryname, Continent continent) {
        this.countryname = countryname;
        this.continent = continent;
    }
}
