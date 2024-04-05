package com.example.todo.execeptions;

import com.example.todo.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExeceptionHandler {
  @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse> genereateException(ResponseStatusException re){
      String message= re.getMessage();
      ApiResponse response=new ApiResponse(message,false);
      return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);

  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String,String>> hanleMethodArgsNotValidException(MethodArgumentNotValidException ex){
    Map<String,String> resp=new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error)->{
      String fieldName=((FieldError)error).getField();
      String message=error.getDefaultMessage();
      resp.put(fieldName,message);

    });
    return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);

  }
}
