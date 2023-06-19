package com.uam.caronex.dto;

import com.uam.caronex.model.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    private String id;
    private Account account;
    private String name;
    private String phoneNumber;
    private LocalDate birthDate;
    private String cnh;
    private Boolean isDriver;
}
