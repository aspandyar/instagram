package com.example.instagram.service;


import com.example.instagram.dto.request.SubscriberDtoRequest;
import com.example.instagram.module.Subscriber;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface SubscriberService {

    Optional<Subscriber> getById(Long id);

    Subscriber getByIdThrowException(Long id);

    List<Subscriber> getAll();

    Subscriber create(Principal principal, Long subscriberUserId);

    void delete(Long id);
}
