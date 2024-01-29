package com.cloud.ventevoiture.model.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.cloud.ventevoiture.model.entity.messages.Discussion;
import com.cloud.ventevoiture.model.entity.messages.Message;
import com.cloud.ventevoiture.model.repository.DiscussionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscussionService {
    private final DiscussionRepository discussionRepository;

    public void insert(Message m){
        Discussion discu = discussionRepository.findByIdSenderAndIdReceiver(m.getId_sender(), m.getId_receiver());
        if(discu != null){
            discu.getMessages().add(m);
            discussionRepository.save(discu);
            System.out.println("hiiiiii");
        }
        else if(discu == null){
            Discussion d = new Discussion();
            List<Message> mess = new ArrayList<>();
            mess.add(m);
            d.setId_sender(m.getId_sender());
            d.setId_receiver(m.getId_receiver());
            d.setMessages(mess);
            System.out.println("huhuuuuu");
            discussionRepository.save(d);
        }

    }

    
}
