package com.example.demo.dto;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RepresentativeDto {

    @Pattern(regexp = "\\d{10}", message = "EGN must be a 10-digit number")
    private String egn;

    private String typeOfIdentificator;

    private String firstName;

    private String middleName;

    private String lastName;

    @Pattern(regexp = "Male|Female", message = "Gender must be 'Male' or 'Female'")
    private String gender;
}