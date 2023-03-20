package com.example.todolistsmaple.controller;

import com.example.todolistsmaple.Dto.TodoRequest;
import com.example.todolistsmaple.sercurity.SecurityUtils;
import com.example.todolistsmaple.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final TodoService todoService;


    @GetMapping("")
    public ResponseEntity<?> getUserById(){
        var au = SecurityUtils.requester();
        return ResponseEntity.ok(todoService.getUserById(au.getId()));
    }

    @PostMapping("/todos")
    public ResponseEntity<?> addTodo(@RequestBody TodoRequest todoRequest){
        var au = SecurityUtils.requester();
        todoService.createNewTodo(todoRequest,au.getId());
        return ResponseEntity.ok("create to do successfully!");
    }
    @PutMapping("/todos/update/{todoId}")
    public ResponseEntity<?> updateTodo(@RequestBody TodoRequest todoRequest,@PathVariable("todoId") Long todoId){
        var au  = SecurityUtils.requester();
        todoService.updateTodo(todoRequest, todoId,au.getId());
        return ResponseEntity.ok("update to do successfully!");
    }
    @DeleteMapping("/todo/{todoId}")
    public boolean deleteTodo(@PathVariable("todoId") Long todoId){
        var au = SecurityUtils.requester();
        return todoService.deleteTodo(todoId, au.getId());
    }
    @DeleteMapping("/delete/user")
    public boolean deleteUser(){
        var au = SecurityUtils.requester();
        return  todoService.deleteUser(au.getId());
    }

}
