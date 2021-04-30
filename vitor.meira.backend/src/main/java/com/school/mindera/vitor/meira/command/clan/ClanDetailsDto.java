package com.school.mindera.vitor.meira.command.clan;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClanDetailsDto {

    private long id;
    private String name;
    private String birthProvince;
}
