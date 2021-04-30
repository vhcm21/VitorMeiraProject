package com.school.mindera.vitor.meira.command.clan;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CreateOrUpdateClanDetailsDto {

    @NotBlank(message = "Must have name of clan")
    private String name;

    @NotBlank(message = "Must have original province of clan")
    private String birthProvince;
}
