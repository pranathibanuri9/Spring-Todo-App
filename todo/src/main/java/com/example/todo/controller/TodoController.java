package com.example.todo.controller;

import com.example.todo.payloads.ApiResponse;
import com.example.todo.payloads.TodoDto;
import com.example.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController

public class TodoController {
    @Autowired
    private TodoService todoService;
    @GetMapping("/todos")
    public ResponseEntity<List<TodoDto>> getAllTodos(){

        return ResponseEntity.ok(this.todoService.getTodos());
    }

@PostMapping("/todos")
public ResponseEntity<TodoDto> createTodo(@Valid  @RequestBody TodoDto todoDto){

    TodoDto createtodo=this.todoService.createTodo(todoDto);
    return  new ResponseEntity<>(createtodo, HttpStatus.CREATED);
}

    @GetMapping("/todos/{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable Integer id){
        return ResponseEntity.ok(this.todoService.getTodoById(id));
    }
    @PutMapping("/todos/{id}")
    public ResponseEntity<TodoDto> updateTodo(@Valid @RequestBody TodoDto todoDto,@PathVariable Integer id){
        TodoDto updateTodo=this.todoService.updateTodo(todoDto,id);
        return ResponseEntity.ok(updateTodo);



    }
    @DeleteMapping("/todos/{id}")

    public ResponseEntity<ApiResponse> deleteTodo(@PathVariable Integer id){
        this.todoService.deleteTodo(id);
        return new ResponseEntity<>(new ApiResponse("user deleted successfully",true),HttpStatus.OK);
    }






}
