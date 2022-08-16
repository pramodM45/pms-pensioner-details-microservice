package com.example.pensionerdetailsmicroservice.service;

import com.example.pensionerdetailsmicroservice.clientproxy.AuthenticationServiceClient;
import com.example.pensionerdetailsmicroservice.exceptions.AadhaarNotFoundException;
import com.example.pensionerdetailsmicroservice.model.JWTValidateRequest;
import com.example.pensionerdetailsmicroservice.model.JWTValidateResponse;

import com.example.pensionerdetailsmicroservice.model.PensionerDetail;
import com.example.pensionerdetailsmicroservice.repo.PensionerDetailsRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PensionerDetailsService {

    @Autowired
    AuthenticationServiceClient authClient;

    @Autowired
    PensionerDetailsRepo pensionerDetailsRepo;

    public boolean validateUser(String token){
        ResponseEntity<JWTValidateResponse> responseEntity = authClient.validateToken(new JWTValidateRequest(token));
        return responseEntity.getBody().isValid();
    }

    public PensionerDetail getPensioner(String aadhaarNumber) {
        Optional<PensionerDetail> opt = pensionerDetailsRepo.findByAadhaarNumber(aadhaarNumber);
        if(opt.isEmpty()){
            throw new AadhaarNotFoundException("aadhar not found for "+aadhaarNumber);
        }
        return opt.get();
    }
}
