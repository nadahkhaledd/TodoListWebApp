package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import server.model.UserRepository;
import server.model.UserService;

import java.util.Map;

@Path("/{name}")
public class UserUpdateController {
    UserService userService = new UserService(new UserRepository());

    /**
     * Accepts JSON object of the form {"newName":"newName"}
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
