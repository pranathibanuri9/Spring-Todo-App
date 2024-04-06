package com.example.todo.service;

import com.example.todo.entity.Todo;
import com.example.todo.payloads.TodoDto;
import com.example.todo.repository.TodoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TodoDto createTodo(TodoDto tododto) {
        Todo todo = this.modelMapper.map(tododto, Todo.class);
        Todo newTodo = this.todoRepository.save(todo);
        return this.modelMapper.map(newTodo, TodoDto.class);

    }

    @Override
    public List<TodoDto> getTodos() {
        List<Todo> todos = this.todoRepository.findAll();
        List<TodoDto> todoDtos = todos.stream().map(todo -> this.modelMapper.map(todo, TodoDto.class)).collect(Collectors.toList());
        return todoDtos;
    }

    @Override
    public TodoDto getTodoById(Integer todoId) {
        Todo todo = this.todoRepository.findById(todoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "todo Id NOT_FOUND"));
        TodoDto todoDto = this.modelMapper.map(todo, TodoDto.class);
        return todoDto;
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Integer todoId) {
        Todo todo = this.todoRepository.findById(todoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "todo Id NOT_FOUND"));
        if (todoDto.getStatus() != null) {
            todo.setStatus(todoDto.getStatus().toUpperCase());
        }

        // Update priority if provided
        if (todoDto.getPriority() != null) {
            todo.setPriority(todoDto.getPriority().toUpperCase());
        }

        // Update todo if provided
        if (todoDto.getTodo() != null) {
            todo.setTodo(todoDto.getTodo());
        }

        Todo updatedTodo = this.todoRepository.save(todo);
        return this.modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public void deleteTodo(Integer todoId) {
        Todo todo = this.todoRepository.findById(todoId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "todo Id NOT_FOUND"));
        this.todoRepository.delete(todo);

    }
}
