package com.portafolio.shop.shop_api.Security.DTO;

import lombok.Data;

@Data
public class AuthResponseDto {
    private String token;

    public AuthResponseDto(String token) {
        this.token = token;
    }
}