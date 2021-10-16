package com.example.map.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String regionname;
    @ManyToOne(optional = false)
    private Country country;

    public Region(String regionname, Country country) {
        this.regionname = regionname;
        this.country = country;
    }
}
