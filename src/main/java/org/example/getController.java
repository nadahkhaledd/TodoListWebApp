package org.example;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import server.todoItems.TodoItem;
import server.todoItems.TodoItemsRepository;
import server.todoItems.TodoItemsService;

import java.util.List;

@Path("get")
public class getController {

    TodoItemsRepository repository = new TodoItemsRepository();
    TodoItemsService todoItemsService=new TodoItemsService(repository);

    @GET
    @Path("useritems")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserItems(@QueryParam("username") String username){
        List<TodoItem> todoItems=todoItemsService.getTodosFromDB(repository.getUserTodos(username));
        if (todoItems.isEmpty())
            return new Response("no items found for this user",204," ");
        return new Response("OK",200,todoItems);
    }

    @GET
    @Path("userlatest")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserLatestItems(@QueryParam("username") String username){
        List<TodoItem> todoItems=todoItemsService.getTodosFromDB(repository.getUserLatestTodos(username));
        if (todoItems.isEmpty())
            return new Response("no items found for this user",204," ");
        return new Response("OK",200,todoItems);
    }

    @GET
    @Path("userfavorites")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUserFavorites(@QueryParam("username") String username){
        List<TodoItem> todoItems=todoItemsService.getTodosFromDB(repository.getFavorites(username));
        if (todoItems.isEmpty())
            return new Response("no items found for this user",204," ");
        return new Response("OK",200,todoItems);
    }


}
