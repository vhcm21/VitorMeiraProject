package com.school.mindera.vitor.meira.command.samurai;

import com.school.mindera.vitor.meira.enumerators.Gender;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateOrUpdateSamuraiDetailsDto {

    @NotBlank(message = "Must have name")
    private String name;

    @NotNull(message = "Must have year of birth")
    private long birthYear;

    @NotNull(message = "Must have year of death")
    private long deathYear;

    @NotNull(message = "Must have gender")
    private Gender gender;
}
