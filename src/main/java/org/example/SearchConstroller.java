package org.example;

import enums.SearchKey;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import server.todoItems.TodoItem;
import server.todoItems.TodoItemsRepository;
import server.todoItems.TodoItemsService;

import java.util.List;

@Path("searchBy")
public class SearchConstroller {
     TodoItemsService todoItemsService=new TodoItemsService(new TodoItemsRepository());
    @GET
    @Path("Title")
    @Produces({MediaType.APPLICATION_JSON})
    public Response searchByTitle(@QueryParam("username") String username,@QueryParam("searchValue") String searchValue){
       List<TodoItem> todoItems=todoItemsService.searchByKey(SearchKey.Title,searchValue,username);
       if (todoItems.isEmpty())
           return new Response("no items with this title",204,todoItems);
        return new Response("done",200,todoItems);
    }
    @GET
    @Path("Priority")
    @Produces({MediaType.APPLICATION_JSON})
    public Response searchByPriority(@QueryParam("username") String username, @QueryParam("searchValue") String searchValue){
        List<TodoItem> todoItems=todoItemsService.searchByKey(SearchKey.Priority,searchValue,username);
        if (todoItems.isEmpty())
            return new Response("no items with the given priority",204,todoItems);
        return new Response("done",200,todoItems);

    }
    @GET
    @Path("EndDate")
    @Produces({MediaType.APPLICATION_JSON})
    public Response searchByEndDate(@QueryParam("username") String username, @QueryParam("searchValue") String searchValue){
        List<TodoItem> todoItems=todoItemsService.searchByKey(SearchKey.EndDate,searchValue,username);

        if(todoItems==null)
            return new Response("invalid date format",400,todoItems);
        else if (todoItems.isEmpty())
            return new Response("no items with the given end date",204,todoItems);

        return new Response("done",200,todoItems);
    }
    @GET
    @Path("StartDate")
    @Produces({MediaType.APPLICATION_JSON})
    public Response searchByStartDate(@QueryParam("username") String username, @QueryParam("searchValue") String searchValue){
        List<TodoItem> todoItems=todoItemsService.searchByKey(SearchKey.StartDate,searchValue,username);
        if(todoItems==null)
            return new Response("invalid date format",400,todoItems);
        else if (todoItems.isEmpty())
            return new Response("no items with the given start date",204,todoItems);

        return new Response("done",200,todoItems);

    }
}
