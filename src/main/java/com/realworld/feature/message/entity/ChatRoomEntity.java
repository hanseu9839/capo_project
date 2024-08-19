package com.realworld.feature.message.entity;


import com.realworld.feature.message.domain.CreateChatRoom;
import com.realworld.feature.product.entity.ProductJpaEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;


@ToString
@Builder
@Entity
@Getter
@Setter
@Table(name="chat_rooms")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoomEntity {

    @Id
    @Column(name="chat_room_id")
    private UUID roomId;

    @OneToOne(fetch = FetchType.LAZY,  cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "lastMessageId")
    private MessageEntity lastMessage;

    @CreatedDate
    @Column(name = "create_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "product_seq", referencedColumnName = "product_seq")
    private ProductJpaEntity product;

    @Column(name = "room_maker_id")
    private String roomMaker;

    @Column(name = "guest_id")
    private String guest;

    public CreateChatRoom createToDomain(){
        return CreateChatRoom.builder()
                .roomId(this.roomId.toString())
                .lastMessage(this.roomId.toString())
                .roomMaker(this.roomMaker)
                .guest(this.guest)
                .productSeq(this.product.getProductSeq())
                .createdAt(String.valueOf(this.createdAt))
                .build();
    }


}
