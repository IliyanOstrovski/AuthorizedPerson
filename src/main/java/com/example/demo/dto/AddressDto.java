package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private Long id;
    private String address;
    private String addressLat;
    private String postCode;
    private String addressType;
    private String settlementCode;
    private String settlementName;
    private String settlementPostCode;
    private String settlementNameLat;
    private String settlementRegionCode;
    private String settlementRegionName;
    private String settlementMunicipalityCode;
    private String settlementMunicipalityName;

}
