package com.uam.caronex.entity;

import com.uam.caronex.model.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
public class UserEntity {

    @Id
    private String id;
    private Account account;
    private String name;
    private String phoneNumber;
    private LocalDate birthDate;
    private String cpf;
    private String cnh;
}