package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import server.model.UserRepository;
import server.model.UserService;

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
    public String updateUsersName(@PathParam("name") String name, Map<String,String> updateParams) {
        boolean updated = userService.updateUsersName(name,updateParams.get("newName"));
        if(updated)
            return "YOUR NAME WAS UPDATED SUCCESSFULLY "+
                    updateParams.get("newName").toUpperCase();
        else{
            return "ERROR - COULD NOT UPDATE NAME";
        }
    }

}
