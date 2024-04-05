package com.example.todo.payloads;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDto {
//    private Integer id;
//    private String todo;
//    private String status;
//    private String priority;
private Integer id;

    @NotBlank(message = "Todo description must not be empty")
    private String todo;

    @NotNull(message = "Status must be provided")
    @Pattern(regexp = "TO DO|IN PROGRESS|DONE", message = "Status must be one of: TO DO, IN PROGRESS, DONE")
    private String status;

    @NotNull(message = "Priority must be provided")
    @Pattern(regexp = "HIGH|MEDIUM|LOW", message = "Priority must be one of: HIGH, MEDIUM, LOW")
    private String priority;
}
