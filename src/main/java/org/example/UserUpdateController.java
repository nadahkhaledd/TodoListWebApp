package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import server.user.UserRepository;
import server.user.UserService;

import java.util.Map;

@Path("users")
public class UserUpdateController {
    UserService userService = new UserService(new UserRepository());

    /**
     * Accepts JSON object of the form {"newName":"newName"}
     */
    @PUT
    @Path("/{name}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUsersName(@PathParam("name") String name, Map<String,String> updateParams) {
        boolean updated = userService.updateUsersName(name,updateParams.get("newName"));
        if(updated) {
            return new Response("YOUR NAME WAS UPDATED SUCCESSFULLY "+
                    updateParams.get("newName").toUpperCase(),200,null);
        }
        else{
            return new Response("COULD NOT UPDATE NAME"+
                    updateParams.get("newName").toUpperCase(),400,null);
        }
    }

}
