package com.cloud.ventevoiture.model.repository;

import java.util.List;

import org.bson.types.ObjectId;
import   org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.cloud.ventevoiture.model.entity.messages.Discussion;
import com.cloud.ventevoiture.model.entity.messages.Message;

@Repository
public interface DiscussionRepository extends MongoRepository<Discussion,ObjectId> {
    @Query("{'id_sender': ?0, 'id_receiver': ?1}")
    List<Discussion> findByIdSenderAndIdReceiver(Integer senderId, Integer receiverId);
    
}
