package com.cloud.ventevoiture.controller.messages;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ventevoiture.model.entity.messages.Discussion;
import com.cloud.ventevoiture.model.entity.messages.Message;
import com.cloud.ventevoiture.model.entity.user.User;
import com.cloud.ventevoiture.model.repository.DiscussionRepository;
import org.springframework.web.bind.annotation.PathVariable;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/discussions")
public class DiscussionController {
    private final DiscussionRepository discussionRepository;

    @GetMapping("/{receiverId}/discussion")
    public ResponseEntity<Object> findByPerson(Authentication authentication,@PathVariable Integer receiverId){
        HashMap<String, Object> map = new HashMap<>();
        try {
            User user = (User) authentication.getPrincipal();
            Discussion messages = discussionRepository.findByIdSenderAndIdReceiver(user.getPerson().getIdPerson(),receiverId);
            map.put("message", "success");
            map.put("messages", messages);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(map);
        }
    }

    @GetMapping
    public ResponseEntity<Object> findAll(Authentication authentication){
        HashMap<String, Object> map = new HashMap<>();
        try {
            User user = (User) authentication.getPrincipal();
            List<Discussion> messages = discussionRepository.findByIdSender(user.getPerson().getIdPerson());
            map.put("message", "success");
            map.put("messages", messages);
            return new ResponseEntity<>(map, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(map);
        }
    }
}
