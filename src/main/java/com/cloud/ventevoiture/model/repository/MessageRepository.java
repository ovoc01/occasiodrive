package com.cloud.ventevoiture.model.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.Query;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.cloud.ventevoiture.model.entity.messages.Message;


public interface MessageRepository extends MongoRepository<Message,ObjectId>{
    @Query("{'id_sender': ?0, 'id_receiver': ?1}")
    List<Message> findByIdSenderAndIdReceiver(Integer senderId, Integer receiverId);
}
