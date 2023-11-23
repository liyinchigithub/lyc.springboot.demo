package com.example.lyc.springboot.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserFormDataDTO {
    private String userName;
    private String password;
}
