package com.example.todo.payloads;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDto {
    private Integer id;

    private String todo;

    @Pattern(regexp = "TO DO|IN PROGRESS|DONE", message = "Status must be one of: TO DO, IN PROGRESS, DONE")
    private String status;

    @Pattern(regexp = "HIGH|MEDIUM|LOW", message = "Priority must be one of: HIGH, MEDIUM, LOW")
    private String priority;
}
