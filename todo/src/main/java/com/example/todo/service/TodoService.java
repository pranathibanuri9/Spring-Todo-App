package com.example.todo.service;

import com.example.todo.payloads.TodoDto;

import java.util.List;

public interface TodoService {

    TodoDto createTodo(TodoDto todo);
    List<TodoDto> getTodos();

    TodoDto getTodoById(Integer todoId);
    TodoDto updateTodo(TodoDto todo, Integer todoId);
    void deleteTodo(Integer todoId);


}
