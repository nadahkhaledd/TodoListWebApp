package server.storage;

import server.model.User;

import java.util.ArrayList;

public interface Storage {
    ArrayList<User> loadData();
    void saveData(ArrayList<User> users);
}
