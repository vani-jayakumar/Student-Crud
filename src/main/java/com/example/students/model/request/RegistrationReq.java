package com.example.students.model.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationReq {
    private String email;
    private String password;
    private String firstName;
    private String lastName;


}
