package com.portafolio.shop.shop_api.Security.DTO;

import lombok.Data;

@Data
public class LoginRequestDto {
    private String username;
    private String password;
}