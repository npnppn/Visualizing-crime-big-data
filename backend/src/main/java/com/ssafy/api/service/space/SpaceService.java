package com.ssafy.api.service.space;

import com.ssafy.api.response.cctv.CountByDongGetRes;

public interface SpaceService {

    public CountByDongGetRes getSpaceCount(String type);
}
