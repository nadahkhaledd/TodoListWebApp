package org.example;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import server.todoItems.TodoItemsRepository;
import server.todoItems.TodoItemsService;

@Path("/todolist")
public class TodoItemDeleteController {

    TodoItemsService todoItemsService = new TodoItemsService(new TodoItemsRepository());

    @DELETE
    @Path("/deleteItem/{title}")
    public Response deleteItem(@PathParam("title") String title, @QueryParam("name") String name) {
        boolean isDeleted = todoItemsService.deleteTodoItem(title, name);
        if(isDeleted)
            return new Response("Item "+title+" deleted successfully.", 200, title);
        else
            return new Response("Item "+title +" couldn't be deleted", 400, title);
    }

}
