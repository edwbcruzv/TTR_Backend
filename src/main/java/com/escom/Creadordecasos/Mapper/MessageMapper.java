package com.escom.Creadordecasos.Mapper;

import com.escom.Creadordecasos.Dto.MessageDto;
import com.escom.Creadordecasos.Entity.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

/**
 * Interfaz utilizada para mapear de los objetos Message y MessageDto
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MessageMapper {

    @Mapping(source = "senderUser.id", target = "senderUserId")
    @Mapping(source = "recvUser.id", target = "recvUserId")
    MessageDto toMessageDto(Message msg);

    @Mapping(source = "senderUserId", target = "senderUser.id")
    @Mapping(source = "recvUserId", target = "recvUser.id")
    Message toMessage(MessageDto msgDto);

    List<MessageDto> toMessageDtoList(List<Message> msgList);

    List<Message> toMessageList(List<MessageDto> msgDtoList);
}
