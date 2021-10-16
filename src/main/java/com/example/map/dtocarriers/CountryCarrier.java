package com.example.map.dtocarriers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CountryCarrier {
    private String countryname;
    private Integer continent_id;
}
