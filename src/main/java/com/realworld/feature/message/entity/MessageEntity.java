package com.realworld.feature.message.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="messages")
@EntityListeners(AuditingEntityListener.class)
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long messageId;

    @JoinColumn(name = "chat_room_id", insertable = false, updatable = false) // 단순히 값만 필요하기에 해당 처리
    private String roomId;

    @JoinColumn(name="user_id", insertable = false, updatable = false)
    private String sender;


    private String message;

    @CreatedDate
    @Column(name="create_at")
    private LocalDateTime createAt;

}
