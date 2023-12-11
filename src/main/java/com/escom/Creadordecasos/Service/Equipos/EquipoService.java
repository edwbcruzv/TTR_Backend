package com.escom.Creadordecasos.Service.Equipos;

import com.escom.Creadordecasos.Dto.EquipoDTO;
import com.escom.Creadordecasos.Entity.Equipo;
import com.escom.Creadordecasos.Entity.Grupo;
import com.escom.Creadordecasos.Mapper.EquipoMapper;
import com.escom.Creadordecasos.Repository.EquipoRepository;
import com.escom.Creadordecasos.Repository.GrupoRepository;
import com.escom.Creadordecasos.Service.Equipos.Bodies.EquipoReq;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EquipoService {
    private final EquipoRepository equipoRepository;
    private final EquipoMapper equipoMapper;
    private final GrupoRepository grupoRepository;

    public ResponseEntity<List<EquipoDTO>> getAllByGrupoId(Long id){
        Optional<Grupo> optionalGrupo  = grupoRepository.findById(id);
        if (optionalGrupo.isPresent()){
            List<Equipo> list_entity = equipoRepository.findByGrupo(optionalGrupo.get());
            List<EquipoDTO> list_dto = equipoMapper.toListDto(list_entity);

            return ResponseEntity.ok(list_dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<EquipoDTO> getById(Long id){
        Optional<Equipo> optionalEquipo = equipoRepository.findById(id);
        if(optionalEquipo.isPresent()) {
            EquipoDTO dto = equipoMapper.toDto(optionalEquipo.get());

            return ResponseEntity.ok(dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }

    public ResponseEntity<EquipoDTO> create(EquipoReq equipoReq){

        Optional<Grupo> optionalGrupo = grupoRepository.findById(equipoReq.getGrupo_id());
        if (optionalGrupo.isPresent()){
            Equipo equipo = Equipo.builder()
                    .nombre(equipoReq.getNombre())
                    .grupo(optionalGrupo.get())
                    .build();

            equipoRepository.save(equipo);
            EquipoDTO dto = equipoMapper.toDto(equipo);
            return ResponseEntity.ok(dto);
        }else{
            return ResponseEntity.badRequest().body(null);
        }
    }


    public ResponseEntity<Boolean> update(EquipoReq equipoReq){
        Optional<Equipo> optionalEquipo = equipoRepository.findById(equipoReq.getId());

        if (!optionalEquipo.isPresent()){
            return ResponseEntity.badRequest().body(false);
        }

        Optional<Grupo> optionalGrupo = grupoRepository.findById(equipoReq.getGrupo_id());
        Equipo equipo = optionalEquipo.get();
        if (optionalGrupo.isPresent()){
            equipo.setNombre(equipoReq.getNombre());
            equipo.setGrupo(optionalGrupo.get());

            equipoRepository.save(equipo);
            return ResponseEntity.ok(true);

        }else{
            return ResponseEntity.badRequest().body(false);
        }
    }


    public ResponseEntity<Boolean> delete(Long id){
        Optional<Equipo> optionalEquipo = equipoRepository.findById(id);
        if(optionalEquipo.isPresent()) {
            equipoRepository.deleteById(id);
            return ResponseEntity.ok(true);
        }else{
            return ResponseEntity.ok(false);
        }

    }

}
