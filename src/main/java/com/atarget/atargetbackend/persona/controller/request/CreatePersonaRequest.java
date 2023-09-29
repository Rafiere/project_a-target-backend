package com.atarget.atargetbackend.persona.controller.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record CreatePersonaRequest(@Email String email, @NotBlank @Length(min = 8) String password) {

}
