package com.example.pensionerdetailsmicroservice.model;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JWTValidateResponse {
    private boolean isValid;
    private String username;
    private String message;
}
