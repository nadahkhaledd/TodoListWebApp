package utility;

import client.app.TodoListClient;
import client.app.UserClient;
import server.model.User;
import server.todoItems.TodoItem;

import java.util.ArrayList;

public class UserUtils {

    TodoListClient todoListClient = new TodoListClient();
    UserClient userClient = new UserClient();

    private User setUserData(String username, ArrayList<TodoItem> items) {
        User user = new User(username);
        user.setItems(items);
        return user;
    }

    public ArrayList<User> loadData() {
        ArrayList<User> users = new ArrayList<>();
       ArrayList<String> usernames = userClient.getUserNames();
        for (String username : usernames) {
            ArrayList<TodoItem> userItems = todoListClient.get(username, "useritems");
            User user = setUserData(username, userItems);
            users.add(user);
        }
        return users;
    }

}
