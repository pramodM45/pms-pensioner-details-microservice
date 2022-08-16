package com.example.pensionerdetailsmicroservice.controller;

import com.example.pensionerdetailsmicroservice.model.PensionerDetail;
import com.example.pensionerdetailsmicroservice.repo.BankDetailsRepo;
import com.example.pensionerdetailsmicroservice.repo.PensionerDetailsRepo;
import com.example.pensionerdetailsmicroservice.service.PensionerDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PensionerDetailsController.class)
class PensionerDetailsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PensionerDetailsService pensionerDetailsService;

    @MockBean
    private PensionerDetailsRepo pensionerDetailsRepo;

    @MockBean
    private BankDetailsRepo bankDetailsRepo;

    @Test
    void getPensionerDetails() throws Exception {

        when(pensionerDetailsService.validateUser(anyString())).thenReturn(true);
        when(pensionerDetailsService.getPensioner(anyString()))
                .thenReturn(PensionerDetail.builder().id(100L).build());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization","test_token");
        RequestBuilder request = MockMvcRequestBuilders
                .get("/PensionerDetailByAadhaar/test_aadharNumber")
                .headers(headers)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request)
                .andExpect(status().isOk());
    }
}