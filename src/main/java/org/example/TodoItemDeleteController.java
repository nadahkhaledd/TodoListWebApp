package org.example;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import server.todoItems.TodoItemsRepository;
import server.todoItems.TodoItemsService;

@Path("/{name}/todolist")
public class TodoItemDeleteController {

    TodoItemsService todoItemsService = new TodoItemsService(new TodoItemsRepository());

    @DELETE
    @Path("/deleteItem/{title}")
    public Response deleteItem(@PathParam("title") String title, @PathParam("name") String name) {
        boolean isDeleted = todoItemsService.deleteTodoItem(title, name);
        if(isDeleted)
            return new Response("Item deleted successfully.", 200, title);
        else
            return new Response("Item couldn't be deleted", 400, title);
    }

}
