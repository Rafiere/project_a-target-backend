package com.atarget.atargetbackend.auth.controller;

import com.atarget.atargetbackend.auth.controller.request.UseTokenForRecoveryPasswordRequest;
import com.atarget.atargetbackend.auth.service.UseTokenForRecoveryPasswordService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequiredArgsConstructor
public class UseTokenForRecoveryPasswordController {

	private final UseTokenForRecoveryPasswordService useTokenForRecoveryPasswordService;

	@PutMapping(path = "/auth/recovery-password/{token}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> execute(@Valid @NotNull @RequestBody final UseTokenForRecoveryPasswordRequest request,
	                                    @NotBlank @PathVariable final String token) {

		useTokenForRecoveryPasswordService.execute(request, token);

		return ResponseEntity.status(204).build();
	}
}
