package com.uam.caronex.dto;

import com.uam.caronex.model.Account;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    @Id
    private String id;
    private Account account;
    private String name;
    private String phoneNumber;
    private LocalDate birthDate;
    private String cpf;
    private String cnh;
    private Boolean isDriver;
}
