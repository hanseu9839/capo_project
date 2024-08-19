package com.realworld.feature.message.repository.message;

import com.realworld.feature.message.entity.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

}
