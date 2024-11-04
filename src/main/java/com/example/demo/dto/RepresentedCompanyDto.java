package com.example.demo.dto;

import java.util.List;

public class RepresentedCompanyDto {

    private Long id;
    private String identifier;
    private String name;
    private List<AddressDto> addresses;
    private List<RepresentativeDto> representatives;

    public RepresentedCompanyDto(Long id, String identifier, String name, List<AddressDto> addresses, List<RepresentativeDto> representatives) {
        this.id = id;
        this.identifier = identifier;
        this.name = name;
        this.addresses = addresses;
        this.representatives = representatives;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressDto> addresses) {
        this.addresses = addresses;
    }

    public List<RepresentativeDto> getRepresentatives() {
        return representatives;
    }

    public void setRepresentatives(List<RepresentativeDto> representatives) {
        this.representatives = representatives;
    }
}

