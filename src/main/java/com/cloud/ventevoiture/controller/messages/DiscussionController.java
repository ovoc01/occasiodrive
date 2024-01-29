package com.cloud.ventevoiture.controller.messages;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.ventevoiture.model.repository.DiscussionRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DiscussionController {
    private final DiscussionRepository discussionRepository;

    @GetMapping
    public void findByPerson(){

    }
}
