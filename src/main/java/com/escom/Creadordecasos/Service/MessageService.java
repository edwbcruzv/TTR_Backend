package com.escom.Creadordecasos.Service;

import com.escom.Creadordecasos.Dto.MessageDto;
import com.escom.Creadordecasos.Mapper.MessageMapper;
import com.escom.Creadordecasos.Repository.MessageRepository;
import com.escom.Creadordecasos.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para la entidad Message
 */
@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;

    private final MessageMapper messageMapper;

    /**
     * Almacena un mensaje en la BD
     *
     * @param messageDto Mensaje a guardar en formato DTO
     * @return True si se guardo con exito, false si ocurrio un error
     */
    public boolean createMessage(MessageDto messageDto) {
        try {
            messageRepository.save(messageMapper.toMessage(messageDto));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Obtiene una lista de los mensajes relacionados a un usuario (ya sea como remitente o receptor)
     *
     * @param userId Id del usuario relacionado a los mensajes
     * @return Lista con objetos MessageDto
     */
    public List<MessageDto> getMessagesFromUserId(Long userId) {
        return messageMapper.toMessageDtoList(messageRepository.findBySenderUser_IdOrRecvUser_Id(userId, userId));
    }

}
