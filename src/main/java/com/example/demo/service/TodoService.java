package com.example.demo.service;


import com.example.demo.model.TodoEntity;
import com.example.demo.repository.TodoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class TodoService {

    @Autowired
    private TodoRepository repository;

    public String testService() {
        TodoEntity entity = TodoEntity.builder().title("My first Todo item").build();

        repository.save(entity);

        TodoEntity savedEntity = repository.findById(entity.getId()).get();

        return savedEntity.getTitle();

    }

    // Todo 아이템 생성
    public List<TodoEntity> create(final TodoEntity entity) {
        validate(entity);

        repository.save(entity);

        log.info("Entity id: {} is saved.", entity.getId());

        return repository.findByUserId(entity.getUserId());

    }

    // 매개변수로 받은 id의 유저가 작성한 Todo 반환
    public List<TodoEntity> retrieve(final String userId) {
        return repository.findByUserId(userId);
    }


    // entity 를 받아오고, 그 정보로 갱신하기 (업데이트)
    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);

        final Optional<TodoEntity> original = repository.findById(entity.getId());

        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());

            repository.save(todo);
        });

        return retrieve(entity.getUserId());
    }

    // entity 삭제
    public List<TodoEntity> delete(final TodoEntity entity) {
        validate(entity);

        try {
            repository.delete(entity);
        } catch (Exception e) {
            log.error("error deleting entity ", entity.getId(), e);
            throw new RuntimeException("error deleting entity " + entity.getId());
        }
        return retrieve(entity.getUserId());
    }

    // entity 검사
    private void validate(final TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getUserId() == null) {
            log.warn("Unknowm user.");
            throw new RuntimeException("Unknowm user.");
        }
    }
}
