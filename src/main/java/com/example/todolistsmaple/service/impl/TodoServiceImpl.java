package com.example.todolistsmaple.service.impl;

import com.example.todolistsmaple.Dto.TodoRequest;
import com.example.todolistsmaple.Dto.UserDto;
import com.example.todolistsmaple.entity.Todo;
import com.example.todolistsmaple.entity.User;
import com.example.todolistsmaple.mapper.TodoMapper;
import com.example.todolistsmaple.repository.TodoRepository;
import com.example.todolistsmaple.repository.UserRepository;
import com.example.todolistsmaple.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {
    private final UserRepository userRepository;

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Override
    @Transactional
    public UserDto getUserById(Long au) {
        var user = userRepository
                .findById(au)
                .orElse(new User());
        return todoMapper.entityToDto(user);
    }

    @Override
    @Transactional
    public void createNewTodo(TodoRequest request,Long au) {
        var user = userRepository
                .findById(au)
                .orElseThrow(() -> new RuntimeException("Not found!"));
        Todo todo = new Todo();
        todo.setContent(request.getContent());
        todo.setStatus(request.getStatus());
        todo.setStartDate(request.getStartDate());
        todo.setEndDate(request.getEndDate());
        user.getTodoList().add(todo);
        userRepository.save(user);
    }
    @Override
    @Transactional
    public void updateTodo(TodoRequest request, Long todoId, Long au){
        var user = userRepository
                .findById(au)
                .orElseThrow(() -> new RuntimeException("Not found!"));
        if (todoId>=1){
            Todo todo = todoRepository
                    .findById(todoId)
                    .orElseThrow(() -> new RuntimeException("Not found!"));
            if (todo!=null){
                todo.setContent(request.getContent());
                todo.setStatus(request.getStatus());
                todo.setStartDate(request.getStartDate());
                todo.setEndDate(request.getEndDate());
                user.getTodoList().add(todo);
                userRepository.save(user);
            }
        }
    }

    @Override
    public boolean deleteUser(Long au) {
        var user = userRepository
                .findById(au)
                .orElseThrow(() -> new RuntimeException("Not found!"));
        if(user!=null){
            userRepository.delete(user);
            return true;
        }
        return false;
    }
    @Override
    public boolean deleteTodo(Long todoId, Long au){
        var user = userRepository
                .findById(au)
                .orElseThrow(() -> new RuntimeException("Not found!"));
        if (todoId>=1){
            Todo todo = todoRepository
                    .findById(todoId)
                    .orElseThrow(() -> new RuntimeException("Not found!"));
            if (todo!=null){
                user.getTodoList().remove(todo);
                todoRepository.delete(todo);
                return true;
            }
        }
        return false;
    }

}
