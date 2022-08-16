package com.example.pensionerdetailsmicroservice.clientproxy;

import com.example.pensionerdetailsmicroservice.exceptions.JWTException;
import com.example.pensionerdetailsmicroservice.model.JWTValidateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        if(response.status()==401){
            return new JWTException("invalid or expired jwt token");
        }
        return null;
    }
}
