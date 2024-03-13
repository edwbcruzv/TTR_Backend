package com.escom.CreadorPracticas.Service.Estudiante.Bodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EstudianteListUsernames {
    private List<String> estudiantesUsernames;
}
