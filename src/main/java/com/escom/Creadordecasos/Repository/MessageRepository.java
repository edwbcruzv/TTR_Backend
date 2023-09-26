package com.escom.Creadordecasos.Repository;

import com.escom.Creadordecasos.Entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findBySenderUser_IdOrRecvUser_Id(Long id,
                                                   Long id1);

}
