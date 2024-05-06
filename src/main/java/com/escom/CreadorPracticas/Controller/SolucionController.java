package com.escom.CreadorPracticas.Controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/solucion")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class SolucionController {
}
