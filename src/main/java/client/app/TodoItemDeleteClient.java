package client.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;

public class TodoItemDeleteClient {

    private final Client client = ClientConnection.client;

    public boolean deleteTodoItem(String name, String title) {
        int statusCode = client.target(ClientConnection.REST_URI)
                .path("/todolist")
                .path("/deleteItem")
                .path(title)
                .queryParam("name", name)
                .request(MediaType.APPLICATION_JSON)
                .delete()
                .readEntity(Response.class)
                .getStatusCode();
        return statusCode == 200;
    }
}
