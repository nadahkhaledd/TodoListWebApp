package client.app.clients;

import client.app.connection.ClientConnection;
import enums.Category;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import org.example.Response;
import server.todoItems.TodoItem;
import utility.Utils;

import java.util.ArrayList;
import java.util.List;


public class TodoListClient {

   private final Utils utils = new Utils();
   private static TodoListClient todoListClient;
   private TodoListClient(){}
    public static TodoListClient getInstance(){
       if(todoListClient==null){
           todoListClient=new TodoListClient();
       }
       return todoListClient;
    }

    //private static final String REST_URI
      //      = "http://localhost:8080/TodoListWebApp/webapi";
    private final Client client = ClientBuilder.newClient();

    public ArrayList<TodoItem> get(String username, String endpoint){
        ArrayList arrayList = (ArrayList) client.target(ClientConnection.REST_URI)
                .path("get").path(endpoint).queryParam("username", username)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class).getItemsToBeReturned();

        return utils.jsonToTodos(arrayList);
    }


    public List<TodoItem> SearchByTitle(String username, String searchValue)  {
        client.app.Response response = client
                .target(ClientConnection.REST_URI+"/searchBy/Title?username="+username+"&searchValue="+searchValue)
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(client.app.Response.class);
        List<TodoItem> result=utils.jsonToTodos(
                (ArrayList<String>) response.getItemsToBeReturned());
        if (result==null||result.isEmpty())
            System.out.println(response.getMessage());
        return result;
    }
    public List<TodoItem> SearchByPriority(String username, String searchValue)  {
        client.app.Response response = client
                .target(ClientConnection.REST_URI+"/searchBy/Priority?username="+username+"&searchValue="+searchValue)
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(client.app.Response.class);
        List<TodoItem> result=utils.jsonToTodos(
                (ArrayList<String>) response.getItemsToBeReturned());
        if (result==null||result.isEmpty())
            System.out.println(response.getMessage());
        return result;
    }
    public List<TodoItem> SearchByStartDate(String username, String searchValue)  {
        client.app.Response response = client
                .target(ClientConnection.REST_URI+"/searchBy/StartDate?username="+username+"&searchValue="+searchValue)
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(client.app.Response.class);
        List<TodoItem> result=utils.jsonToTodos(
                (ArrayList<String>) response.getItemsToBeReturned());
        if (result==null||result.isEmpty())
            System.out.println(response.getMessage());
        return result;
    }
    public List<TodoItem> SearchByEndDate(String username, String searchValue)  {
        client.app.Response response = client
                .target(ClientConnection.REST_URI+"/searchBy/EndDate?username="+username+"&searchValue="+searchValue)
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(client.app.Response.class);
        List<TodoItem> result=utils.jsonToTodos(
                (ArrayList<String>) response.getItemsToBeReturned());
        if (result==null||result.isEmpty())
            System.out.println(response.getMessage());
        return result;
    }
    public boolean updateTodoItem(String name,TodoItem item, String oldTitle){
        return client
                .target(ClientConnection.REST_URI)
                .path(name)
                .path("todolist")
                .path(oldTitle)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(item, MediaType.APPLICATION_JSON))
                .readEntity(Response.class)
                .getStatusCode()/100 == 2;
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
    public client.app.Response deleteTodoItem(String name, String title) {
        client.app.Response response = client.target(ClientConnection.REST_URI)
                .path("/todolist")
                .path("/deleteItem")
                .path(title)
                .queryParam("name", name)
                .request(MediaType.APPLICATION_JSON)
                .delete()
                .readEntity(client.app.Response.class);
        return response;
    }
    public client.app.Response createTodoItem(String name, TodoItem todoItem) {
        client.app.Response statusCode = client.target(ClientConnection.REST_URI)
                .path("/todolist")
                .path("/createItem")
                .queryParam("name", name)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(todoItem, MediaType.APPLICATION_JSON))
                .readEntity(client.app.Response.class);
        return statusCode;
    }
}
