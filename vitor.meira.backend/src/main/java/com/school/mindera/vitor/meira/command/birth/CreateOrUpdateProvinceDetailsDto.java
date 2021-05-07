package com.school.mindera.vitor.meira.command.birth;

import lombok.Builder;
import lombok.Data;


import javax.validation.constraints.NotBlank;

@Data
@Builder
public class CreateOrUpdateProvinceDetailsDto {

    @NotBlank(message = "Must have name of province")
    private String name;
}
