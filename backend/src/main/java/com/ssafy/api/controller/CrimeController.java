package com.ssafy.api.controller;

import com.ssafy.api.request.arrstrate.GetCrimeListReq;
import com.ssafy.api.response.BaseResponseBody;
import com.ssafy.api.response.arrestrate.GuCrimeListRes;
import com.ssafy.api.response.arrestrate.TotalCrimeListGetRes;
import com.ssafy.api.response.riskindex.RiskIndexListGetRes;
import com.ssafy.api.service.arrestrate.ArrestRateService;
import com.ssafy.api.service.riskindex.RiskIndexService;
import io.swagger.annotations.Api;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/v1/crime")
@Api(value = "ando")
@RequiredArgsConstructor
public class CrimeController {

    final ArrestRateService arrestRateService;

    final RiskIndexService riskIndexService;

    @GetMapping
    public ResponseEntity<?> getCrimeList(GetCrimeListReq getCrimeListReq){

        try {
            if(getCrimeListReq.getGu_id() == null && getCrimeListReq.getYear() == 0)
                return ResponseEntity.status(HttpStatus.OK).body(TotalCrimeListGetRes.of(HttpStatus.OK.value(), "Success", arrestRateService.getTotalCrimeList()));
            else if(getCrimeListReq.getGu_id() == null)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TotalCrimeListGetRes.of(HttpStatus.BAD_REQUEST.value(), "Gu_id is not correct!",null));
            else if(getCrimeListReq.getGu_id() <= 0 || getCrimeListReq.getGu_id() >= 26){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TotalCrimeListGetRes.of(HttpStatus.BAD_REQUEST.value(), "Gu_id is not correct!",null));
            }else if(getCrimeListReq.getYear() <= 2017 || getCrimeListReq.getYear() >= 2021)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(TotalCrimeListGetRes.of(HttpStatus.BAD_REQUEST.value(), "Year is not correct!",null));
            else{
                return ResponseEntity.status(HttpStatus.OK).body(GuCrimeListRes.of(HttpStatus.OK.value(), "Success",arrestRateService.getGuCrimeList(getCrimeListReq.getGu_id(), getCrimeListReq.getYear())));
            }
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(BaseResponseBody.of(HttpStatus.INTERNAL_SERVER_ERROR.value(),e.getMessage()));
        }
    }


    @GetMapping("/forecast/{sigungu}")
    public ResponseEntity<RiskIndexListGetRes> getRiskIndexList(@PathVariable Long sigungu){
        try {
            if(sigungu <= 0 || sigungu >=26 )
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RiskIndexListGetRes.of(HttpStatus.BAD_REQUEST.value(), "Gu_id is not correct!",null));
            else
                return ResponseEntity.status(HttpStatus.OK).body(RiskIndexListGetRes.of(HttpStatus.OK.value(), "Success", riskIndexService.getRiskIndexList(sigungu)));
        }catch (NotFoundException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(RiskIndexListGetRes.of(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage(),null));
        }
    }





}
