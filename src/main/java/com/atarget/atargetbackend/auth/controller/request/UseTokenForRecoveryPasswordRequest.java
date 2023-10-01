package com.atarget.atargetbackend.auth.controller.request;

import jakarta.validation.constraints.NotBlank;

public record UseTokenForRecoveryPasswordRequest(@NotBlank String newPassword) {

}
