package com.cloud.ventevoiture.model.entity.messages;
import java.time.Instant;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cloud.ventevoiture.model.entity.user.User;

import lombok.Data;

@Data
@Document(collection = "me  ssages")
public class Message {
    @Id
    ObjectId id;
    Integer id_sender;
    Integer id_receiver;
    String messages;
    Instant date_creation = Instant.now();  
}
