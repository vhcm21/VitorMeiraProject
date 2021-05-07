package com.school.mindera.vitor.meira.service;

import com.school.mindera.vitor.meira.command.clan.ClanDetailsDto;
import com.school.mindera.vitor.meira.command.clan.CreateOrUpdateClanDetailsDto;
import com.school.mindera.vitor.meira.exception.ClanAlreadyExistsException;

public interface ClanService {

    ClanDetailsDto createClan(CreateOrUpdateClanDetailsDto createOrUpdateClanDetailsDto) throws ClanAlreadyExistsException;
}
