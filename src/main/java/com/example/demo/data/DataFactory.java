package com.example.demo.data;

import com.example.demo.dto.AddressDto;
import com.example.demo.dto.RepresentativeDto;
import com.example.demo.dto.RepresentedCompanyDto;

import java.util.Collections;
import java.util.List;

public class DataFactory {

    public static List<RepresentativeDto> getSampleRepresentatives() {
        return List.of(
                new RepresentativeDto("1234567890", "ID", "John", "A.", "Doe", "Male"),
                new RepresentativeDto("0987654321", "ID", "Jane", "B.", "Smith", "Female")
        );
    }

    public static List<RepresentedCompanyDto> getSampleCompanies() {
        List<AddressDto> addressList = List.of(new AddressDto(3L, "Улица 1", "Street one", "8994",
                "HEAD_OFFICE", "00045", "Мокрен", "8994",
                "Мокрен", "SLV", "Сливен", "SLV11", "Котел"));

        return List.of(
                new RepresentedCompanyDto(1L, "131423633", "Microsoft", addressList, getSampleRepresentatives()),
                new RepresentedCompanyDto(2L, "452398457", "Google", addressList, Collections.emptyList())
        );
    }
}
