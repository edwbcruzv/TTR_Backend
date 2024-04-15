package com.escom.CreadorPracticas.Service.Grupo;

import com.escom.CreadorPracticas.Dto.GrupoDTO;
import com.escom.CreadorPracticas.Entity.Grupo;
import com.escom.CreadorPracticas.Entity.Profesor;
import com.escom.CreadorPracticas.Mapper.GrupoMapper;
import com.escom.CreadorPracticas.Repository.GrupoRepository;
import com.escom.CreadorPracticas.Repository.ProfesorRepository;
import com.escom.CreadorPracticas.Service.Grupo.Bodies.GrupoReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GrupoService {
    private final GrupoRepository grupoRepository;
    private final GrupoMapper grupoMapper;
    private final ProfesorRepository profesorRepository;
    private final ClaveManagerService claveManagerService;

    public ResponseEntity<List<GrupoDTO>> getAllByProfesorUsername(String username){
        Optional<Profesor> optionalProfesor = profesorRepository.findByUsername(username);
        if (optionalProfesor.isPresent()){
            List<Grupo> list_entity = grupoRepository.findByProfesor(optionalProfesor.get());
            List<GrupoDTO> list_dto = grupoMapper.toListDto(list_entity);

            return ResponseEntity.ok(list_dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<GrupoDTO> getById(Long id){
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        if(optionalGrupo.isPresent()) {
            GrupoDTO dto = grupoMapper.toDto(optionalGrupo.get());

            return ResponseEntity.ok(dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<GrupoDTO> create(GrupoReq grupoReq){

        Optional<Profesor> optionalProfesor = profesorRepository.findByUsername(grupoReq.getProfesorUsername());
        if (optionalProfesor.isPresent()){
            LocalDateTime now = LocalDateTime.now();
            Grupo grupo = Grupo.builder()
                    .codigo(claveManagerService.generarClave())
                    .fechaVencimientoCodigo(now.plus(2, ChronoUnit.DAYS))
                    .clave(grupoReq.getClave())
                    .nombre(grupoReq.getNombre())
                    .profesor(optionalProfesor.get())
                    .build();
            grupoRepository.save(grupo);
            GrupoDTO dto = grupoMapper.toDto(grupo);
            return ResponseEntity.ok(dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<Boolean> update(GrupoReq grupoReq){
        Optional<Grupo> optionalGrupo = grupoRepository.findById(grupoReq.getId());

        if (!optionalGrupo.isPresent()){
            return ResponseEntity.badRequest().body(false);
        }

        Optional<Profesor> optionalProfesor = profesorRepository.findByUsername(grupoReq.getProfesorUsername());
        Grupo grupo = optionalGrupo.get();
        if (optionalProfesor.isPresent()){
            grupo.setNombre(grupoReq.getNombre());
            grupo.setProfesor(optionalProfesor.get());
            grupo.setClave(grupoReq.getClave());
            grupoRepository.save(grupo);
            return ResponseEntity.ok(true);

        }else{
            return ResponseEntity.badRequest().body(false);
        }
    }



    public ResponseEntity<Boolean> delete(Long id){
        Optional<Grupo> optionalGrupo = grupoRepository.findById(id);
        if(optionalGrupo.isPresent()) {
            grupoRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }
    }
}
