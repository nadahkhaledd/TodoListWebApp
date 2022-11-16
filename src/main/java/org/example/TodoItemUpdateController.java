package org.example;

import enums.Category;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import server.todoItems.*;

@Path("/{name}/todolist")
public class TodoItemUpdateController {

    TodoItemsService todoListService = new TodoItemsService(new TodoItemsRepository());
    @PUT
    @Path("/{title}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateTodoItem(@PathParam("name") String name,@PathParam("title") String oldTitle,
                                    TodoItem todoItem) {
        boolean updated = todoListService.updateTodoItem(name,todoItem,oldTitle);
        if(updated) {
            return "ITEM WAS UPDATED SUCCESSFULLY";
        }
        else{
            return "ERROR - COULD NOT UPDATE ITEM";
        }
    }

    @PUT
    @Path("/{title}/fav")
    @Produces(MediaType.APPLICATION_JSON)
    public String addItemToFavorites(@PathParam("name") String name,@PathParam("title") String title) {
        boolean updated = todoListService.addItemToFavorite(name,title);
        if(updated) {
            return "ITEM WAS ADDED TO FAVORITES SUCCESSFULLY";
        }
        else{
            return "ERROR - COULD NOT ADD ITEM TO FAVORITES";
        }
    }

    @PUT
    @Path("/{title}/category")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String addItemToCategory(@PathParam("name") String name, @PathParam("title") String title,
                                    Category category) {
        boolean updated = todoListService.addItemToCategory(name,title,category);
        if(updated) {
            return "ITEM WAS ADDED TO CATEGORY SUCCESSFULLY";
        }
        else{
            return "ERROR - COULD NOT ADD ITEM TO CATEGORY";
        }
    }
}
