package com.example.map.dtocarriers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictCarrier {
    private String districtname;
    private Integer region_id;
}
