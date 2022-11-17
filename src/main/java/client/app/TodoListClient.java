package client.app;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.Category;
import enums.Priority;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import org.example.Response;
import server.todoItems.TodoItem;
import utility.DateUtils;
import utility.Utils;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class TodoListClient {

   private final Utils utils = new Utils();

    private static final String REST_URI
            = "http://localhost:8080/TodoListWebApp/webapi";
    private final Client client = ClientBuilder.newClient();

    public ArrayList<TodoItem> get(String username, String endpoint){
        ArrayList arrayList = (ArrayList) client.target(REST_URI)
                .path("get").path(endpoint).queryParam("username", username)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class).getItemsToBeReturned();

        return utils.jsonToTodos(arrayList);
    }


    public List<TodoItem> SearchByTitle(String username, String searchValue)  {
        client.app.Response response = client
                .target(REST_URI+"/searchBy/Title?username="+username+"&searchValue="+searchValue)
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
                .target(REST_URI+"/searchBy/Priority?username="+username+"&searchValue="+searchValue)
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
                .target(REST_URI+"/searchBy/StartDate?username="+username+"&searchValue="+searchValue)
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
                .target(REST_URI+"/searchBy/EndDate?username="+username+"&searchValue="+searchValue)
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(client.app.Response.class);
        List<TodoItem> result=utils.jsonToTodos(
                (ArrayList<String>) response.getItemsToBeReturned());
        if (result==null||result.isEmpty())
            System.out.println(response.getMessage());
        return result;
    }
}
