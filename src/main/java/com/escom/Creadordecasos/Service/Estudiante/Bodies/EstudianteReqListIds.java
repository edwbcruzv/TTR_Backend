package com.escom.Creadordecasos.Service.Estudiante.Bodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EstudianteReqListIds {
    private List<String> estudiantes_usernames;
}
