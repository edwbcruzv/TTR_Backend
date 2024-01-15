package com.escom.Creadordecasos.Service.RecursosMultimedia.Bodies;

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
    private Long usuario_id;
    private Long caso_estudio_id;
    private String descripcion;
    private Integer numero_orden;
    private MultipartFile archivoMultimedia;
}

