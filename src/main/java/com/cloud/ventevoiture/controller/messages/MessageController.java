package com.cloud.ventevoiture.controller.messages;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cloud.ventevoiture.model.entity.messages.Message;
import com.cloud.ventevoiture.model.entity.user.User;
import com.cloud.ventevoiture.model.repository.MessageRepository;
import com.cloud.ventevoiture.model.services.DiscussionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/messages")
public class MessageController {
    private final MessageRepository messagerepository;
    private final DiscussionService discussionService;

    @GetMapping
    public ResponseEntity<Object> findAll() {
        try {
            List<Message> messages = messagerepository.findAll();
            System.out.println(messages);
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "success");
            map.put("messages", messages);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping
    public ResponseEntity<Object> save(Authentication authentication,@RequestBody Message m){
        HashMap<String, Object> map = new HashMap<>();
        try {
            User user = (User) authentication.getPrincipal();
            // messagerepository.save(m);
            m.setId_sender(user.getPerson().getIdPerson());
            discussionService.insert(m);
            map.put("Insertion", "success");
             return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("erreur", e.getMessage());
            return ResponseEntity.badRequest().body(map);
        }

    }

    @GetMapping("/{receiverId}/messages")
    public ResponseEntity<Object> getMessagesBySenderAndReceiver(Authentication authentication, @PathVariable Integer receiverId) {
        try {
            User user = (User) authentication.getPrincipal();
            List<Message> messages = messagerepository.findByIdSenderAndIdReceiver(user.getPerson().getIdPerson(), receiverId);
            System.out.println(messages);
            HashMap<String, Object> map = new HashMap<>();
            map.put("message", "success");
            map.put("messages", messages);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
