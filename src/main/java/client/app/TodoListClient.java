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


public class TodoListClient {

    Utils utils = new Utils();

    private static final String REST_URI
            = "http://localhost:8080/TodoListWebApp/webapi";
    private final Client client = ClientBuilder.newClient();


    public ArrayList<TodoItem> getItems(String username){
        ArrayList arrayList = (ArrayList) client.target(REST_URI)
                .path("get").path("useritems").queryParam("username", username)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class).getItemsToBeReturned();

        return utils.jsonToTodos(arrayList);
    }

    public ArrayList<TodoItem> getLatestItems(String username){
        ArrayList arrayList = (ArrayList) client.target(REST_URI)
                .path("get").path("userlatest").queryParam("username", username)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class).getItemsToBeReturned();

        return utils.jsonToTodos(arrayList);
    }

    public ArrayList<TodoItem> getFavorites(String username){
        ArrayList arrayList = (ArrayList) client.target(REST_URI)
                .path("get").path("userfavorites").queryParam("username", username)
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class).getItemsToBeReturned();

        return utils.jsonToTodos(arrayList);
    }

}
