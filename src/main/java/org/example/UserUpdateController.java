package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import server.user.UserRepository;
import server.user.UserService;

import java.util.Map;

@Path("/{name}")
public class UserUpdateController {
    UserService userService = new UserService(new UserRepository());


    /**
     * Accepts a string of the new name
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUsersName(@PathParam("name") String name, String newName) {
        boolean updated = userService.updateUsersName(name,newName.trim());
        if(updated) {
            return new Response("YOUR NAME WAS UPDATED SUCCESSFULLY "+
                    newName.trim().toUpperCase(), 200);
        }
        else{
            return new Response("COULD NOT UPDATE NAME",400);
        }
    }


}
