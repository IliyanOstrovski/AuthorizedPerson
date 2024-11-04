package com.example.demo.controllers;

import com.example.demo.dto.RepresentativeDto;
import com.example.demo.dto.ResponseStatusDto;
import com.example.demo.services.RepresentativeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.example.demo.dto.RepresentedCompanyDto;

@RestController
@RequestMapping
public class RepresentativeController {

    private final RepresentativeService representativeService;

    @Autowired
    public RepresentativeController(RepresentativeService representativeService) {
        this.representativeService = representativeService;
    }

    @Tag(name = "ФЛ връща данни за всички ЮЛ, за които това ФЛ е упълномощено лице.")
    @Operation(
            summary = "Получаване фирмата с която е свързано упълномощеното лице!",
            description = "Връща списък с фирми, където даденото физическо лице е упълномощено лице."
    )
    @GetMapping("/persons/{personIdentifier}/representatives")
    public ResponseEntity<List<RepresentedCompanyDto>> getRepresentedCompaniesByPersonIdentifier(@PathVariable String personIdentifier) {
        List<RepresentedCompanyDto> representedCompanies = representativeService.findRepresentedCompaniesByPersonIdentifier(personIdentifier);
        return new ResponseEntity<>(representedCompanies, HttpStatus.OK);
    }

    @Tag(name = "За ЮЛ извлича списък с УЛ.")
    @Operation(
            summary = "Получаване на фирма и упълномощени лица",
            description = "Връща информация за фирма и списък от всички упълномощени лица, свързани с нея."
    )
    @GetMapping("/companies/{companyIdentifier}/representatives")
    public ResponseEntity<List<RepresentativeDto>> getRepresentativesByCompanyIdentifier(@PathVariable String companyIdentifier) {
        List<RepresentativeDto> representatives = representativeService.findRepresentativesByCompanyIdentifier(companyIdentifier);
        return new ResponseEntity<>(representatives, HttpStatus.OK);
    }

    @PostMapping("/companies/{companyIdentifier}/representatives")
    @Tag(name = "Уведомява НБД за създадено упълномощено лице в Лаб. \n")
    @Operation(summary = "Регистрация на Упълномощено лице / Представител", description = "Попълнете полето долу")
    public ResponseEntity<ResponseStatusDto> addRepresentative(
            @PathVariable String companyIdentifier,
            @RequestBody RepresentativeDto representativeDto,
            @RequestHeader(value = "AZ-Return-Error", required = false) String returnErrorHeader) {

        if (returnErrorHeader != null) {
            ResponseStatusDto errorStatus = new ResponseStatusDto(false, "some error message");
            return ResponseEntity.ok(errorStatus);
        }

        ResponseStatusDto responseStatus = representativeService.addRepresentativeToCompany(companyIdentifier, representativeDto);

        if (!responseStatus.isSuccess()) {
            System.err.println("Error: " + responseStatus.getMessage());
        }
        return ResponseEntity.ok(responseStatus);
    }

    @PutMapping("/companies/{companyIdentifier}/representatives")
    @Tag(name = "Записва всички промени за Упълномощените лица. ")
    @Operation(summary = "Обновяване на Упълномощено лице / Представител", description = "Попълнете полето долу")
    public ResponseEntity<ResponseStatusDto> updateRepresentatives(
            @PathVariable String companyIdentifier,
            @RequestBody List<RepresentativeDto> updatedRepresentatives,
            @RequestHeader(value = "AZ-Return-Error", required = false) String returnErrorHeader) {

        if (returnErrorHeader != null) {
            ResponseStatusDto errorStatus = new ResponseStatusDto(false, "some error message");
            return ResponseEntity.ok(errorStatus);
        }

        ResponseStatusDto responseStatus = representativeService.updateRepresentativesForCompany(companyIdentifier, updatedRepresentatives);

        if (!responseStatus.isSuccess()) {
            System.err.println("Error: " + responseStatus.getMessage());
        }
        return ResponseEntity.ok(responseStatus);
    }

}

