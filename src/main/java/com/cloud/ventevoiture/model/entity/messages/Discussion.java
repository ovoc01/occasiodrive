package com.cloud.ventevoiture.model.entity.messages;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Document(collection = "discussions")
public class Discussion {
    @Id
    ObjectId id;
    Integer id_sender;
    Integer id_receiver;
    List<Message> messages;

}
