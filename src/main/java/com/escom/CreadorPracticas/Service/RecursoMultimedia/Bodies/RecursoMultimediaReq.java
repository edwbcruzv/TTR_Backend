package com.escom.CreadorPracticas.Service.RecursoMultimedia.Bodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RecursoMultimediaReq {
    private Long id;
    private String nombre;
    private MultipartFile fileMultimedia;
    private Long practicaId;
}

