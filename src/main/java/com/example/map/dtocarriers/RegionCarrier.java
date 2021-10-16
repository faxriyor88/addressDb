package com.example.map.dtocarriers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegionCarrier {
    private String regionname;
    private Integer country_id;
}
