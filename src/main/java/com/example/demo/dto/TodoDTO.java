package com.example.demo.dto;

import com.example.demo.model.TodoEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
    private String id; 
    private String title; 
    private boolean done; 

    // 사용자에게 갈 정보 
    public TodoDTO(final TodoEntity entity) {
        this.id = entity.getId(); 
        this.title = entity.getTitle(); 
        this.done = entity.isDone(); 
    }
}
