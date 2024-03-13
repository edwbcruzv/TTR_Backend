package com.escom.CreadorPracticas.Service.RecursoMultimedia.Bodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RecursoMultimediaReqListIds {
    private List<Long> multimediaIds;
}
