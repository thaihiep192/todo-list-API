package com.example.todolistsmaple.mapper;

import com.example.todolistsmaple.Dto.TodoResponse;
import com.example.todolistsmaple.Dto.UserDto;
import com.example.todolistsmaple.entity.Todo;
import com.example.todolistsmaple.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface TodoMapper {
    @Mapping(target = "todos", source = "todoList", qualifiedByName = "mapTodoList")
    UserDto entityToDto(User user);
    @Named("mapTodoList")
    default TodoResponse mapTodoList(Todo item){
        return TodoResponse
                .builder()
                .id(item.getId())
                .content(item.getContent())
                .status(item.getStatus())
                .createdDate(item.getCreatedDate())
                .modifiedDate(item.getModifiedDate())
                .startDate(item.getStartDate())
                .endDate(item.getEndDate())
                .build();
    }
}
