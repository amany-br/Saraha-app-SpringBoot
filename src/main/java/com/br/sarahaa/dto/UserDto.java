package com.br.sarahaa.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_EMPTY)
public class UserDto implements Serializable {

    private Integer id;

    // pseudo name -> auto-generated
    private String userName;

    @NotBlank(message = "You need to provide the first name")
    @NotNull
    private String firstName;

    @NotBlank(message = "You need to provide the last name")
    @NotNull
    private String lastName;

    // @NotNull(message = "You need to provide the birth date")
    // @Past(message = "The birthdate should be in the past")
    private LocalDate birthDate;

    @NotBlank(message = "You need to provide an email")
    @Email(message = "You need to provide a valid email address")
    @NotNull
    private String email;

    @NotBlank(message = "You need to provide the password")
    @Size(min = 8, max = 16, message = "The password should be between 8 and 16 chars")
    @NotNull
    private String password;

}
