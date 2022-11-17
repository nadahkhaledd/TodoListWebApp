package org.example;

import enums.Category;
import enums.Priority;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import server.todoItems.*;
import utility.DateUtils;

import java.util.Date;
import java.util.Map;

@Path("/{name}/todolist")
public class TodoItemUpdateController {

    TodoItemsService todoListService = new TodoItemsService(new TodoItemsRepository());



    /**
     * Accepts JSON object of the attributes of TodoItem to be changed{"tite":"title"....}
     */
    @PUT
    @Path("/{title}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTodoItem(@PathParam("name") String name,@PathParam("title") String oldTitle,
                                    Map<String,String> todoItemMap) {
        DateUtils dateUtils = new DateUtils();
        Date startDate = dateUtils.convertStringToSQLDate(todoItemMap.get("startDate"));
        Date endDate = dateUtils.convertStringToSQLDate(todoItemMap.get("endDate"));
        Priority priority = Priority.valueOf(todoItemMap.get("priority"));
        Category category = Category.valueOf(todoItemMap.get("category"));
        TodoItem todoItem = new TodoItem(todoItemMap.get("title"),todoItemMap.get("description"),
                priority,category,startDate,endDate);
        todoItem.setFavorite(Boolean.parseBoolean(todoItemMap.get("isFavorite")));
        boolean updated = todoListService.updateTodoItem(name,todoItem,oldTitle);
        if(updated) {
            return new Response("ITEM WAS UPDATED SUCCESSFULLY",200);
        }
        else{
            return new Response("COULD NOT UPDATE ITEM",400);
        }
    }

    @PUT
    @Path("/{title}/fav")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItemToFavorites(@PathParam("name") String name,@PathParam("title") String title) {
        boolean updated = todoListService.addItemToFavorite(name,title);
        if(updated) {
            return new Response("ITEM WAS ADDED TO FAVORITES SUCCESSFULLY",200);
        }
        else{
            return new Response("COULD NOT ADD ITEM TO FAVORITES",400);
        }
    }

    @PUT
    @Path("/{title}/category")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addItemToCategory(@PathParam("name") String name, @PathParam("title") String title,
                                    Category category) {
        boolean updated = todoListService.addItemToCategory(name,title,category);
        if(updated) {
            return new Response("ITEM WAS ADDED TO CATEGORY SUCCESSFULLY",200);
        }
        else{
            return new Response("COULD NOT ADD ITEM TO CATEGORY",400);

        }
    }
}
