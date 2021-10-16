package com.example.map.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String districtName;
    @ManyToOne(optional = false)
    private Region region;

    public District(String districtName, Region region) {
        this.districtName = districtName;
        this.region = region;
    }
}
