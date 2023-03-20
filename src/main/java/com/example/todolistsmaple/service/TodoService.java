package com.example.todolistsmaple.service;

import com.example.todolistsmaple.Dto.TodoRequest;
import com.example.todolistsmaple.Dto.UserDto;

public interface TodoService {
    UserDto getUserById(Long au);

    void  createNewTodo(TodoRequest request, Long au);

    void updateTodo(TodoRequest request, Long todoId, Long au);

    boolean deleteUser (Long au);
    boolean deleteTodo(Long todoId, Long au);
}
