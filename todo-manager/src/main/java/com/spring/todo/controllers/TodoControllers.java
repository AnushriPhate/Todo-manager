package com.spring.todo.controllers;

import com.spring.todo.models.Todo;
import com.spring.todo.services.TodoService;
import com.spring.todo.services.impl.TodoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/todos")
public class TodoControllers {

    Logger logger = LoggerFactory.getLogger(TodoControllers.class);
    @Autowired
    private TodoService todoService;
    Random random = new Random();
    @PostMapping
    public ResponseEntity<Todo> createTodoHandler(@RequestBody Todo todo){

        int id = random.nextInt(9999999);
        todo.setId(id);
        Date currentDate = new Date();
        logger.info("current date: {}", currentDate);
        logger.info("todo date {}", todo.getTodoDate());
        todo.setAddedDate(currentDate);
        logger.info("Create Todo");
        Todo todo1 = todoService.createTodo(todo);
        return new ResponseEntity<>(todo1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodoHandler(@RequestBody Todo todo) {
        List<Todo> allTodos = todoService.getAllTodos();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getSingleTodoHandler(@PathVariable int todoId) throws ParseException {
        Todo todo = todoService.getTodo(todoId);
        return ResponseEntity.ok(todo);
    }
    @PutMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoHandler(@RequestBody Todo todoWithNewDetails, @PathVariable int todoId){
        Todo todo = todoService.updateTodo(todoId, todoWithNewDetails);
        return ResponseEntity.ok(todo);
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<String> deleteTodoHandler(@PathVariable int todoId){
        logger.info("Deleting todo");
        todoService.deleteTodo(todoId);
        return ResponseEntity.ok("Todo successfully deleted");
    }

//    @ExceptionHandler(NullPointerException.class)
//    public ResponseEntity<String> nullPointerExceptionHandler(NullPointerException ex){
//        System.out.println(ex.getMessage());
//        System.out.println("Null pointer exception generated");
//        return new ResponseEntity<>("Null pointer exception generated" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }
//
//    @ExceptionHandler(NumberFormatException.class)
//    public ResponseEntity<String> numberFormatExceptionHandler(NullPointerException ex){
//        System.out.println(ex.getMessage());
//        System.out.println("Number format exception generated");
//        return new ResponseEntity<>("Number format exception generated" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

//    @ExceptionHandler(value = {NullPointerException.class, NumberFormatException.class})
//    public ResponseEntity<String> nullPointerAndNumberFormatExceptionHandler(NullPointerException ex){
//        System.out.println(ex.getMessage());
//        System.out.println("Null pointer exception generated");
//        return new ResponseEntity<>("Null pointer exception generated" + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
