package org.example;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import server.user.UserRepository;
import server.user.UserService;

import java.util.Map;

@Path("/user")
public class UserCreateContoller {

    UserService userService = new UserService(new UserRepository());

    @POST
    @Path("/createUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(@QueryParam("name") String name) {
        boolean isCreated = userService.addUser(name);
        if(isCreated)
            return new Response("Item created successfully.", 201, name);
        else
            return new Response("Item couldn't be created.", 400, name);
    }


}
