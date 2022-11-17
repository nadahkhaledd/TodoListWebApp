package client.app;

import org.example.Response;
import server.todoItems.TodoItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        Simulator simulator = new Simulator();
//        simulator.start();

        TodoListClient todoListClient = new TodoListClient();

        ArrayList<TodoItem> result = todoListClient.get("nadah", "userlatest");

        result.forEach(System.out::println);

    }
}
