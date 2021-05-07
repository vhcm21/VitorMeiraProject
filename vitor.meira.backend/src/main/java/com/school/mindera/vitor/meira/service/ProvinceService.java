package com.school.mindera.vitor.meira.service;

import com.school.mindera.vitor.meira.command.birth.CreateOrUpdateProvinceDetailsDto;
import com.school.mindera.vitor.meira.command.birth.ProvinceDetailsDto;
import com.school.mindera.vitor.meira.exception.ProvinceAlreadyExistsException;

public interface ProvinceService {

    ProvinceDetailsDto createProvince(CreateOrUpdateProvinceDetailsDto createOrUpdateProvinceDetailsDto) throws ProvinceAlreadyExistsException;
}
