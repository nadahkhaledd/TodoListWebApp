package org.example;

import enums.SearchKey;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import server.todoItems.TodoItem;
import server.todoItems.TodoItemsRepository;
import server.todoItems.TodoItemsService;

import java.util.List;

public class getController {

    TodoItemsRepository repository = new TodoItemsRepository();
    TodoItemsService todoItemsService=new TodoItemsService(repository);

    @GET
    @Path("getuseritems")
    @Produces({MediaType.APPLICATION_JSON})
    public Response searchByTitle(@QueryParam("username") String username){
        List<TodoItem> todoItems=todoItemsService.getTodosFromDB(repository.getUserTodos(username));
        if (todoItems.isEmpty())
            return new Response("no items found for this user",204," ");
        return new Response("OK",200,todoItems);
    }
}
