package com.spring.todo.controllers;

import com.spring.todo.models.Todo;
import com.spring.todo.services.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoControllers {

    Logger logger = LoggerFactory.getLogger(TodoControllers.class);
    @Autowired
    private TodoService todoService;
    Random random = new Random();
    @PostMapping
    public Todo createTodoHandler(@RequestBody Todo todo){
        int id = random.nextInt(9999999);
        todo.setId(id);
        logger.info("Create Todo");
        Todo todo1 = todoService.createTodo(todo);
        return todo1;
    }
}
