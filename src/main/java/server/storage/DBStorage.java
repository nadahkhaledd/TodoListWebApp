package server.storage;

import server.model.User;
import server.model.UserRepository;
import server.todoItems.TodoItemsRepository;
import server.todoItems.TodoItemsService;
import utility.DateUtils;
import utility.Utils;

import java.sql.ResultSet;
import java.util.ArrayList;

public class DBStorage implements Storage{
    TodoItemsRepository repository;
    UserRepository userRepository;
    TodoItemsService itemsService;

    public DBStorage(){
        repository = new TodoItemsRepository();
        itemsService = new TodoItemsService(repository);
        userRepository=new UserRepository();
    }


    private User setUserData(String username) {
        ResultSet result = repository.getUserTodos(username);
        DateUtils dateUtils = new DateUtils();
        Utils utils = new Utils();
        User user = new User(username);
        user.setItems(itemsService.getTodosFromDB(result));
        return user;
    }

    @Override
    public ArrayList<User> loadData() {
        ArrayList<User> users = new ArrayList<>();
        ArrayList<String> userNames = userRepository.getUserNames();
        for (String username : userNames) {
            User user = setUserData(username);
            users.add(user);
        }

        return users;
    }

    @Override
    public void saveData(ArrayList<User> users) {

    }
}
