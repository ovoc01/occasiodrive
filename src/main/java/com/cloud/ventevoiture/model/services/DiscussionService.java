package com.cloud.ventevoiture.model.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cloud.ventevoiture.model.entity.messages.Discussion;
import com.cloud.ventevoiture.model.entity.messages.Message;
import com.cloud.ventevoiture.model.entity.user.User;
import com.cloud.ventevoiture.model.repository.DiscussionRepository;
import com.cloud.ventevoiture.model.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiscussionService {
    private final DiscussionRepository discussionRepository;
    private final UserRepository userRepository;

    public void insert(Message m) throws Exception{
        checkIfExist(m.getId_receiver());
        checkIfExist(m.getId_sender());
        Discussion discu = discussionRepository.findByIdSenderAndIdReceiver(m.getId_sender(), m.getId_receiver());
        if(discu != null){
            discu.getMessages().add(m);
            discussionRepository.save(discu);
        }
        else if(discu == null){
            Discussion d = new Discussion();
            List<Message> mess = new ArrayList<>();
            mess.add(m);
            d.setId_sender(m.getId_sender());
            d.setId_receiver(m.getId_receiver());
            d.setMessages(mess);
            discussionRepository.save(d);
        }
    }

    public void checkIfExist(Integer id) throws Exception{
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            System.out.println("haaaaaaa");
            throw new Exception("Cet Id ne correspond a aucun user");
        }
    }

    
}
