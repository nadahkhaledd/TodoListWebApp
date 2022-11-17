package client.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import enums.Category;
import enums.Priority;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import server.todoItems.TodoItem;
import utility.Utils;
import java.util.ArrayList;
import java.util.List;


public class SearchRestClient {
    private String REST_URI
            = "http://localhost:8080/TodoListWebApp/webapi/searchBy";
    //http://localhost:8080/TodoListWebApp/webapi/searchBy/Title?username=Hagar&searchValue=test
    private Client client;
    private Utils utils;
    public SearchRestClient() {
        client = ClientBuilder.newClient();
        utils=new Utils();
    }
    public List<TodoItem> SearchByTitle(String username, String searchValue)  {
        Response response = client
                .target(REST_URI+"/Title?username="+username+"&searchValue="+searchValue)
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(client.app.Response.class);
        List<TodoItem> result=utils.jsonToTodos(
                (ArrayList<String>) response.getItemsToBeReturned());
        if (result.isEmpty())
            System.out.println(response.getMessage());
        return result;
    }
    public List<TodoItem> SearchByPriority(String username, String searchValue)  {
        Response response = client
                .target(REST_URI+"/Priority?username="+username+"&searchValue="+searchValue)
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(client.app.Response.class);
        List<TodoItem> result=utils.jsonToTodos(
                (ArrayList<String>) response.getItemsToBeReturned());
        if (result.isEmpty())
            System.out.println(response.getMessage());
        return result;
    }
    public List<TodoItem> SearchByStartDate(String username, String searchValue)  {
        Response response = client
                .target(REST_URI+"/StartDate?username="+username+"&searchValue="+searchValue)
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(client.app.Response.class);
        List<TodoItem> result=utils.jsonToTodos(
                (ArrayList<String>) response.getItemsToBeReturned());
        if (result.isEmpty())
            System.out.println(response.getMessage());
        return result;
    }
    public List<TodoItem> SearchByEndDate(String username, String searchValue)  {
        Response response = client
                .target(REST_URI+"/EndDate?username="+username+"&searchValue="+searchValue)
                .request(MediaType.APPLICATION_JSON)
                .get().readEntity(client.app.Response.class);
        List<TodoItem> result=utils.jsonToTodos(
                (ArrayList<String>) response.getItemsToBeReturned());
        if (result.isEmpty())
            System.out.println(response.getMessage());
        return result;
    }

}
