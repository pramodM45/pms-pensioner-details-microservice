package com.example.pensionerdetailsmicroservice.controller;

import com.example.pensionerdetailsmicroservice.clientproxy.AuthenticationServiceClient;
import com.example.pensionerdetailsmicroservice.model.JWTValidateRequest;
import com.example.pensionerdetailsmicroservice.model.JWTValidateResponse;
import com.example.pensionerdetailsmicroservice.model.PensionerDetail;

import com.example.pensionerdetailsmicroservice.service.PensionerDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@Slf4j
public class PensionerDetailsController {


    @Autowired
    PensionerDetailsService pensionerDetailsService;

    @GetMapping("/PensionerDetailByAadhaar/{aadhaarNumber}")
    public ResponseEntity<PensionerDetail> getPensionerDetails(@PathVariable String aadhaarNumber,
                                              @RequestHeader("Authorization") String token){
        log.info(aadhaarNumber);
        boolean isValid = pensionerDetailsService.validateUser(token);
        if(isValid){
            PensionerDetail pensionerDetail = pensionerDetailsService.getPensioner(aadhaarNumber);
            return new ResponseEntity<>(pensionerDetail,HttpStatus.OK);
        }
        return null;
    }

    @GetMapping("/health")
    public ResponseEntity<?> healthCheck(){
        HashMap<String,String> map = new HashMap<>();
        map.put("Status","UP");
        return ResponseEntity.ok(map);
    }
}
