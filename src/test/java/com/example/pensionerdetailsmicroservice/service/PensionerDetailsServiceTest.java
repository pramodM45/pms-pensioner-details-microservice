package com.example.pensionerdetailsmicroservice.service;

import com.example.pensionerdetailsmicroservice.clientproxy.AuthenticationServiceClient;
import com.example.pensionerdetailsmicroservice.model.JWTValidateResponse;
import com.example.pensionerdetailsmicroservice.model.PensionerDetail;
import com.example.pensionerdetailsmicroservice.repo.PensionerDetailsRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;



@ExtendWith(MockitoExtension.class)
@Slf4j
class PensionerDetailsServiceTest {


    @Mock
    private AuthenticationServiceClient authClient;

    @Mock
    private PensionerDetailsRepo pensionerDetailsRepo;

    @InjectMocks
    private PensionerDetailsService pensionerDetailsService;

    @Test
    void validateUser() {
        when(authClient.validateToken(any())).thenReturn(ResponseEntity.ok(JWTValidateResponse.builder()
                .isValid(true).build()));
        boolean isValid = pensionerDetailsService.validateUser("test_token");
        assertThat(isValid).isTrue();
    }

    @Test
    void getPensioner() {
        when(pensionerDetailsRepo.findByAadhaarNumber(anyString()))
                .thenReturn(Optional.of(PensionerDetail.builder()
                        .id(100L).build()));
        PensionerDetail pensionerDetail = pensionerDetailsService.getPensioner("test_aadhaarNumber");
        assertThat(pensionerDetailsService.getPensioner("test_aadhaarNumber").getId())
                .isEqualTo(100L);
    }
}