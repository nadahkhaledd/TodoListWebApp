package client.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.example.Response;
import server.todoItems.TodoItem;

import java.util.ArrayList;

public class UserClient {

    private static final String REST_URI
            = "http://localhost:8080/TodoListWebApp/webapi";
    private final Client client = ClientBuilder.newClient();

    public ArrayList<String> getUserNames(){
        return (ArrayList) client.target(REST_URI)
                .path("get").path("usernames")
                .request(MediaType.APPLICATION_JSON)
                .get(Response.class).getItemsToBeReturned();
    }
}
