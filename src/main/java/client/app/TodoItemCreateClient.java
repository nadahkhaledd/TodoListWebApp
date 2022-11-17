package client.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import server.todoItems.TodoItem;

public class TodoItemCreateClient {

    private final Client client = ClientConnection.client;

    public boolean createTodoItem(String name, TodoItem todoItem) {
        int statusCode = client.target(ClientConnection.REST_URI)
                .path("/todolist")
                .path("/createItem")
                .queryParam("name", name)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(todoItem, MediaType.APPLICATION_JSON))
                .readEntity(Response.class)
                .getStatusCode();
        return statusCode == 201;
    }

}
