package server.storage;

import server.user.User;

import java.util.ArrayList;

public interface Storage {
    ArrayList<User> loadData();
    void saveData(ArrayList<User> users);
}
