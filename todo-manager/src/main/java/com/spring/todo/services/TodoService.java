package com.spring.todo.services;

import com.spring.todo.models.Todo;

import java.util.List;

public interface TodoService {
    public Todo createTodo(Todo todo);

    public List<Todo> getAllTodos();

    public Todo getTodo(int todoId);

    public Todo updateTodo(int todoId,Todo todo);
    public void deleteTodo(int todoId);
}
