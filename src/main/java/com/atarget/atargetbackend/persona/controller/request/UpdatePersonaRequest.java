package com.atarget.atargetbackend.persona.controller.request;

import jakarta.validation.constraints.NotBlank;

public record UpdatePersonaRequest(@NotBlank String newNickname) {

}
