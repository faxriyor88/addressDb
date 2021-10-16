package com.example.map.dtocarriers;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressCarrier {
    private String homeNumber;
    private String street;
    private Integer district_id;

}
