package server.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public boolean addUser(String name) {
        return userRepository.createUser(name);
    }

    public boolean updateUsersName(String name, String newName){
        boolean updated = userRepository.updateUsersName(name,newName);
        if(updated){
            return true;
        }
        return false;
    }

    public ArrayList<String> getUserNames(){
        ResultSet result = userRepository.getUserNames();

        ArrayList<String> usernames = new ArrayList<>();

        try {
            while (result.next())
                usernames.add(result.getString(1));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usernames;
    }
}
