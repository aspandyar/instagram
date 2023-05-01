package com.example.instagram.service;

import com.example.instagram.dto.request.SubscriberDtoRequest;
import com.example.instagram.exception.CustomExceptionMessage;
import com.example.instagram.exception.custom.NotFoundException;
import com.example.instagram.exception.custom.RepositoryDeleteException;
import com.example.instagram.exception.custom.RepositoryUpdateException;
import com.example.instagram.module.Subscriber;
import com.example.instagram.module.security.User;
import com.example.instagram.repository.SubscriberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class SubscriberServiceImpl implements SubscriberService {

    private final SubscriberRepository subscriberRepository;

    private final UserService userService;
    @Override
    public Optional<Subscriber> getById(Long id) {
        return subscriberRepository.findById(id);
    }

    @Override
    public Subscriber getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> new NotFoundException(CustomExceptionMessage.NOT_FOUND_EXCEPTION_MESSAGE));
    }

    private Subscriber save(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    @Override
    public List<Subscriber> getAll() {
        return subscriberRepository.findAll();
    }

    @Override
    public Subscriber create(Principal principal, Long subscriberUserId) {
        Subscriber subscriber = new Subscriber();

        try {
            String username = principal.getName();

            User user =  userService.getByUsernameThrowException(username);

            User suscriberUser = userService.getByIdThrownException(subscriberUserId);

            subscriber.setSubscriberUser(suscriberUser);
            subscriber.setUser(user);

            return this.save(subscriber);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryUpdateException(CustomExceptionMessage.CREATE_EXCEPTION_MESSAGE);
        }
    }

    @Override
    public void delete(Long id) {
        Subscriber subscriber = this.getByIdThrowException(id);

        try {
            subscriberRepository.delete(subscriber);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RepositoryDeleteException(CustomExceptionMessage.DELETE_EXCEPTION_MESSAGE);
        }
    }
}
