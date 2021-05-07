package com.school.mindera.vitor.meira.command.samurai;

import com.school.mindera.vitor.meira.enumerators.Gender;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SamuraiDetailsDto {

    private long id;
    private String name;
    private long birthYear;
    private long deathYear;
    private Gender gender;
    private long clanId;
    private long provinceId;
}
