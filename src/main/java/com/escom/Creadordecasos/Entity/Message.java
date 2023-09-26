package com.escom.Creadordecasos.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Entidad Mensaje
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "content", length = 1000)
    private String content;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "sending_date", nullable = false)
    private Date sendingDate;

    @ManyToOne
    @JoinColumn(name = "sending_user_id", nullable = false)
    private User senderUser;

    @ManyToOne(optional = false)
    @JoinColumn(name = "recv_user_id", nullable = false)
    private User recvUser;
}