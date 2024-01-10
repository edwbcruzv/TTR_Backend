package com.escom.Creadordecasos.Service.Grupo;

import com.escom.Creadordecasos.Dto.GrupoDTO;
import com.escom.Creadordecasos.Dto.ProfesorDTO;
import com.escom.Creadordecasos.Entity.Grupo;
import com.escom.Creadordecasos.Entity.Profesor;
import com.escom.Creadordecasos.Mapper.GrupoMapper;
import com.escom.Creadordecasos.Repository.GrupoRepository;
import com.escom.Creadordecasos.Repository.ProfesorRepository;
import com.escom.Creadordecasos.Service.Grupo.Bodies.GrupoReq;
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

    public ResponseEntity<List<GrupoDTO>> getAllByProfesorId(Long id){
        Optional<Profesor> optionalProfesor = profesorRepository.findById(id);
        if (optionalProfesor.isPresent()){
            List<Grupo> list_entity = grupoRepository.findByProfesor(optionalProfesor.get());
            List<GrupoDTO> list_dto = grupoMapper.toListDto(list_entity);

            return ResponseEntity.ok(list_dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<List<GrupoDTO>> getAll(){

        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //User userAuthenticated = (User) authentication.getPrincipal();
        List<Grupo> grupoList = grupoRepository.findAll();
        List<GrupoDTO> list_dto = grupoMapper.toListDto(grupoList);
        return ResponseEntity.ok(list_dto);
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

        Optional<Profesor> optionalProfesor = profesorRepository.findById(grupoReq.getProfesor_id());
        if (optionalProfesor.isPresent()){
            LocalDateTime now = LocalDateTime.now();
            Grupo grupo = Grupo.builder()
                    .clave(claveManagerService.generarClave())
                    .nombre_grupo(grupoReq.getNombre_grupo())
                    .nombre_materia(grupoReq.getNombre_materia())
                    .profesor(optionalProfesor.get())
                    .visible(grupoReq.getVisible())
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

        Optional<Profesor> optionalProfesor = profesorRepository.findById(grupoReq.getProfesor_id());
        Grupo grupo = optionalGrupo.get();
        if (optionalProfesor.isPresent()){
            grupo.setNombre_grupo(grupoReq.getNombre_grupo());
            grupo.setProfesor(optionalProfesor.get());
            grupo.setNombre_materia(grupoReq.getNombre_materia());
            grupo.setVisible(grupoReq.getVisible());

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
