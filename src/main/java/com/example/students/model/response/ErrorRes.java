package com.example.students.model.response;

import org.springframework.http.HttpStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorRes {
    HttpStatus httpStatus;
    String message;


}
