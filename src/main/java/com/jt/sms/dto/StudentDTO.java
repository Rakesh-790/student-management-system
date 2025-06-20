package com.jt.sms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    @Positive
    @Min(100)
    private int roll;
    @NotEmpty
    @NotBlank
    @NotNull
    private String name;

    @Email(message = "Email should be valid")
    private String email;

    @Positive
    private double fees;

    @NotEmpty
    @NotBlank
    @NotNull
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Phone number should be valid format")
    private String phoneNumber;
}
