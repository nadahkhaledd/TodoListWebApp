package client.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.MediaType;
import org.example.Response;

public class UserUpdateClient {
    private Client client = ClientConnection.client;
    private static  UserUpdateClient userUpdateClient = new UserUpdateClient();

    private UserUpdateClient() {
    }

    public boolean updateUsersName(String oldName, String newName){
        return client
                .target(ClientConnection.REST_URI)
                .path(oldName)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.entity(newName, MediaType.APPLICATION_JSON))
                .readEntity(Response.class)
                .getStatusCode()/100 == 2 ;
    }
    public static UserUpdateClient getInstance(){
        return userUpdateClient;
    }

    /*public static void main(String[] args){
        UserUpdateClient u = new UserUpdateClient();
        System.out.println(u.updateUsersName("Sarah","Miand"));
    }*/

}
