package server.storage;

import server.user.User;
import server.user.UserRepository;
import server.user.UserService;
import server.todoItems.TodoItemsRepository;
import server.todoItems.TodoItemsService;
import java.util.ArrayList;

public class DBStorage implements Storage{
    TodoItemsRepository repository;
    UserRepository userRepository;

    UserService userService;
    TodoItemsService itemsService;

    public DBStorage(){
        repository = new TodoItemsRepository();
        itemsService = new TodoItemsService(repository);
        userRepository=new UserRepository();
        userService = new UserService(userRepository);
    }


//    private User setUserData(String username) {
//        ResultSet result = repository.getUserTodos(username);
//        DateUtils dateUtils = new DateUtils();
//        Utils utils = new Utils();
//        User user = new User(username);
//        user.setItems(itemsService.getTodosFromDB(result));
//        return user;
//    }
//
//    @Override
//    public ArrayList<User> loadData() {
//        ArrayList<User> users = new ArrayList<>();
//        ArrayList<String> userNames = userService.getUserNames();
//        for (String username : userNames) {
//            User user = setUserData(username);
//            users.add(user);
//        }
//
//        return users;
//    }


    @Override
    public ArrayList<User> loadData() {
        return null;
    }

    @Override
    public void saveData(ArrayList<User> users) {

    }
}
