package com.ssafy.api.service.cctv;

import com.ssafy.api.response.cctv.SpaceCorrelationGetRes;
import com.ssafy.db.mapping.CctvInfoMapping;
import com.ssafy.db.repository.arrestRateRepository;
import com.ssafy.db.repository.cctvRepository;
import com.ssafy.db.repository.dongRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CCTVServiceImpl implements CCTVService {

    @Autowired
    cctvRepository cctvRepository;
    @Autowired
    dongRepository dongRepository;
    @Autowired
    arrestRateRepository arrestRateRepository;

    @Override
    public List<CctvInfoMapping> getCCTVInfoByDongId(Long dongId) {
        return cctvRepository.findAllByDongId(dongId);
    }

    @Override
    public SpaceCorrelationGetRes getCCTVCorrelation() {
        SpaceCorrelationGetRes res = new SpaceCorrelationGetRes();
        res.setCountList(dongRepository.findCCTVGroupBySiGunGu_Id());
        res.setArrestList(arrestRateRepository.findArrestCntByGu());
        return res;
    }
}