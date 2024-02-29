package com.spring.todo.dao;

import com.spring.todo.helper.Helper;
import com.spring.todo.models.Todo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class TodoDao {

    Logger logger = LoggerFactory.getLogger(TodoDao.class);

    private JdbcTemplate template;

    public TodoDao(@Autowired JdbcTemplate template) {
        this.template = template;

        String createTable = "create table IF NOT EXISTS todos (id int primary key, title varchar(100) not null, content varchar(500), status varchar (10) not null, addedDate datetime, todoDate datetime )";
        int update = template.update(createTable);
        logger.info("TODO TABLE CREATED {} ", update);
    }

    public JdbcTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JdbcTemplate template) {
        this.template = template;
    }

    public Todo saveTodo(Todo todo){
        String insertQuery = " insert into todos(id, title, content, status, addedDate, todoDate) values(?,?,?,?,?,?) ";

        int rows = template.update(insertQuery, todo.getId(), todo.getTitle(), todo.getContent(), todo.getStatus(), todo.getAddedDate(), todo.getTodoDate());

        logger.info("JDBC OPERATION: {} inserted", rows);

        return todo;
    }

    public Todo getTodo(int id) throws ParseException {
        String query = "select * from todos WHERE id = ?";
        Map<String, Object> todoData = template.queryForMap(query, id);

        Todo todo = new Todo();

        todo.setId((int)(todoData.get("id")));
        todo.setTitle((String)(todoData.get("title")));
        todo.setContent((String)(todoData.get("content")));
        todo.setStatus((String)(todoData.get("status")));
        todo.setAddedDate(Helper.parseDate((LocalDateTime) (todoData.get("addedDate"))));
        todo.setTodoDate(Helper.parseDate((LocalDateTime) (todoData.get("todoDate"))));





        return todo;
    }
}
