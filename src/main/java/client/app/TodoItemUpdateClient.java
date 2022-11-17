package client.app;

import enums.Category;
import enums.Priority;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import org.example.Response;
import server.todoItems.TodoItem;
import utility.Utils;

import java.util.Map;

public class TodoItemUpdateClient {
    private Client client = ClientConnection.client;
    Utils utils = new Utils();



    public boolean updateTodoItem(String name,TodoItem item, String oldTitle){

        Map<String,String> todoItemMap = utils.todoItemToMap(item);
        return client
                .target(ClientConnection.REST_URI)
                .path(name)
                .path("todolist")
                .path(oldTitle)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(todoItemMap, MediaType.APPLICATION_JSON))
                .readEntity(Response.class)
                .getStatusCode()/100 == 2 ;
    }
    public boolean addItemToCategory(String name , String title, Category category){
        return client
                .target(ClientConnection.REST_URI)
                .path(name)
                .path("todolist")
                .path(title)
                .path("category")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(category, MediaType.APPLICATION_JSON))
                .readEntity(Response.class)
                .getStatusCode()/100 == 2 ;
    }
    public boolean addItemToFavorites(String name , String title){
        return client
                .target(ClientConnection.REST_URI)
                .path(name)
                .path("todolist")
                .path(title)
                .path("fav")
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity("true", MediaType.APPLICATION_JSON))
                .readEntity(Response.class)
                .getStatusCode()/100 == 2 ;
    }
    /*public static void main(String[] args){
        TodoItemUpdateClient c = new TodoItemUpdateClient();
        //System.out.println(c.addItemToFavorites("Miand","this title"));
        System.out.println(c.addItemToCategory("Miand","this title",Category.Chores));
        //DateUtils dateUtils = new DateUtils();
        //Date startDate = dateUtils.convertStringToDate("10-10-2022");
        //System.out.println(dateUtils.convertDateToString(startDate));
        //TodoItem t = new TodoItem("this title","description", Priority.High,Category.People,
        //        startDate,startDate);

        //t.setFavorite(false);
        //System.out.println(c.updateTodoItem("Miand",t,"this title"));
    }*/
}
