package com.example.demo.services;

import com.example.demo.data.DataFactory;
import com.example.demo.dto.RepresentativeDto;
import com.example.demo.dto.RepresentedCompanyDto;
import com.example.demo.dto.ResponseStatusDto;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class RepresentativeService {
    private final ConcurrentHashMap<String, List<RepresentativeDto>> companyRepresentativeUpdate = new ConcurrentHashMap<>();

    private final ConcurrentHashMap<String, List<RepresentativeDto>> personRepresentative = new ConcurrentHashMap<>();

    private final List<RepresentedCompanyDto> companies = DataFactory.getSampleCompanies();

    private final Map<String, List<RepresentativeDto>> companyRepresentatives = new ConcurrentHashMap<>();

    public RepresentativeService() {
        companies.forEach(company ->
                personRepresentative.put(company.getIdentifier(), company.getRepresentatives()));
    }

    public List<RepresentativeDto> findRepresentativesByCompanyIdentifier(String companyIdentifier) {
        return personRepresentative.getOrDefault(companyIdentifier, List.of());
    }

    public List<RepresentedCompanyDto> findRepresentedCompaniesByPersonIdentifier(String personIdentifier) {
        return companies.stream()
                .filter(company -> personRepresentative.get(company.getIdentifier()).stream()
                        .anyMatch(rep -> rep.getEgn().equals(personIdentifier)))
                .collect(Collectors.toList());
    }
    public ResponseStatusDto addRepresentativeToCompany(String companyIdentifier, RepresentativeDto representativeDto) {
        companyRepresentatives.putIfAbsent(companyIdentifier, new ArrayList<>());
        companyRepresentatives.get(companyIdentifier).add(representativeDto);
        return new ResponseStatusDto(true, "Representative added successfully.");
    }

    public ResponseStatusDto updateRepresentativesForCompany(String companyIdentifier, List<RepresentativeDto> updatedRepresentatives) {
        if (companyRepresentatives.containsKey(companyIdentifier)) {
            companyRepresentatives.put(companyIdentifier, updatedRepresentatives);
            return new ResponseStatusDto(true, "Representatives updated successfully.");
        } else {
            return new ResponseStatusDto(false, "Company not found.");
        }
    }
}