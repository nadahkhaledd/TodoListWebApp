package client.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import server.user.User;

public class UserCreateClient {

    private final Client client = ClientConnection.client;

    public boolean createUser(String name) {
        int statusCode = client.target(ClientConnection.REST_URI)
                .path("/user")
                .path("/createUser")
                .queryParam("name", name)
                .request(MediaType.APPLICATION_JSON)
                .post(Entity.entity(new User(name), MediaType.APPLICATION_JSON))
                .readEntity(Response.class)
                .getStatusCode();
        return statusCode == 201;
    }

}
