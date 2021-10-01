package com.ssafy.api.service.arrestrate;

import com.ssafy.db.dto.arrestrate.GetGuCrimeListDto;
import com.ssafy.db.dto.arrestrate.GetRateDto;
import com.ssafy.db.dto.arrestrate.GetTotalCrimeListDto;
import javassist.NotFoundException;

import java.util.List;

public interface ArrestRateService {

    List<GetTotalCrimeListDto> getTotalCrimeList() throws NotFoundException;

    List<GetGuCrimeListDto> getGuCrimeList(Long id,int year) throws NotFoundException;

    GetRateDto getRate(Long id,int year);
}
