package server.todoItems;

import enums.Category;
import enums.Priority;
import enums.SearchKey;
import ui.Font;
import utility.DateUtils;
import utility.Utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

public class TodoItemsService {
    private final TodoItemsRepository repository;
    private Font font;

    private static TodoItemsService todoService;
    public static TodoItemsService getInstance(){
        if (todoService==null){
            todoService=new TodoItemsService();
        }
        return todoService;
    }
    private TodoItemsService() {
        this.repository = TodoItemsRepository.getInstance();
        this.font = new Font();

    }

    public ArrayList<TodoItem> getItemsByPriority(Priority priority, ArrayList<TodoItem> userItems) {
        ArrayList<TodoItem> result = (ArrayList<TodoItem>) userItems.stream()
                .filter(item -> item.getPriority() == priority).collect(Collectors.toList());
        return result;
    }

    public ArrayList<TodoItem> getItemsByFavorite(ArrayList<TodoItem> userItems) {
        ArrayList<TodoItem> favorites = (ArrayList<TodoItem>) userItems.stream()
                .filter(TodoItem::isFavorite).collect(Collectors.toList());
        return favorites;
    }

    public ArrayList<TodoItem> getItemsByStartDate(Date startDate, ArrayList<TodoItem> userItems) {
        ArrayList<TodoItem> result = (ArrayList<TodoItem>) userItems.stream()
                .filter(item -> item.getStartDate().equals(startDate)).collect(Collectors.toList());
        return result;
    }

    public ArrayList<TodoItem> getItemsByEndDate(Date endDate, ArrayList<TodoItem> userItems) {
        ArrayList<TodoItem> result = (ArrayList<TodoItem>) userItems.stream()
                .filter(item -> item.getEndDate().equals(endDate)).collect(Collectors.toList());
        return result;
    }

    public boolean addTodoItem(String name, TodoItem item) {
        return repository.createUserTodo(name, item);
    }

    public boolean updateTodoItem(String name,TodoItem item, String oldTitle){
        //repo.update
        return repository.updateTodoItem(name,item,oldTitle);

    }

    public boolean deleteTodoItem(String title, String name) {
        return repository.deleteTodoItem(title, name);
    }

    public void showAllTodoItems(ArrayList<TodoItem> userTodoItems) {
        if (userTodoItems.isEmpty())
            System.out.println(font.ANSI_RED + "Sorry , no items available " + font.ANSI_RESET);
        else
            userTodoItems.forEach(System.out::println);
    }


    public ArrayList<TodoItem> getTodosFromDB(ResultSet result) {
        ArrayList<TodoItem> todos = new ArrayList<>();

        DateUtils dateUtils = new DateUtils();
        Utils utils = new Utils();
        try {
            TodoItem todo;
            while (result.next()) {
                if (result.getString(1) == null) {
                    break;
                }
                String currentFormat = "yyyy-MM-dd";
                todo = new TodoItem();
                todo.setTitle(result.getString("title"));
                todo.setDescription(result.getString("description"));
                todo.setPriority(Priority.valueOf(utils.capitalizeFirstLetter(result.getString("priority"))));
                todo.setCategory(Category.valueOf(utils.capitalizeFirstLetter(result.getString("category"))));
                todo.setFavorite(result.getInt("isFavorite") == 1);
                todo.setStartDate(dateUtils.changeFormat(currentFormat, result.getDate("startDate")));
                todo.setEndDate(dateUtils.changeFormat(currentFormat, result.getDate("endDate")));
                todos.add(todo);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return todos;
    }


    public void showTop5ItemsByDate(String username) {
        ResultSet result = repository.getUserLatestTodos(username);
        ArrayList<TodoItem> items = getTodosFromDB(result);
        items.forEach(System.out::println);
    }

    /// Nadah: needs modification
    private void printListItems(int lastIndex, ArrayList<TodoItem> userTodoItems) {
        for (int i = 0; i < lastIndex; i++) {
            System.out.println(userTodoItems.get(i));
        }
    }

    public int getItemByTitle(String title, ArrayList<TodoItem> userTodoItems) {
        for (int i = 0; i < userTodoItems.size(); i++) {
            if (userTodoItems.get(i).getTitle().equalsIgnoreCase(title)) {
                return i;
            }
        }
        return -1;
    }
    public ArrayList<TodoItem>searchByKey(SearchKey searchKey, String searchValue,String username){
        ArrayList<TodoItem> userTodos = null;
        switch (searchKey) {
            case Title:
                userTodos = getTodosFromDB(repository.searchByTitle(username, searchValue));
                break;
            case Priority:
                userTodos = getTodosFromDB(repository.searchByPriority(username, searchValue));
                break;
            case StartDate:
                try {
                    Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchValue);
                    userTodos = getTodosFromDB(repository.searchByStartDate(username, searchValue));
                } catch (ParseException e) {
                    System.out.println(font.ANSI_RED + "invalid date format" + font.ANSI_RESET);

                }
                break;
            case EndDate:
                try {
                    Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchValue);
                    userTodos = userTodos = getTodosFromDB(repository.searchByEndDate(username, searchValue));
                } catch (ParseException e) {
                    System.out.println(font.ANSI_RED + "invalid date format" + font.ANSI_RESET);

                }
                break;
        }
        return userTodos;
    }
    public ArrayList<TodoItem> searchShowItemsBySearchKey(SearchKey searchKey, String searchValue, ArrayList<TodoItem> userTodoItems) {
        ArrayList<TodoItem> returnedItems = new ArrayList<>();
        switch (searchKey) {
            case Title:
                int returnedIndex = getItemByTitle(searchValue, userTodoItems);
                if (returnedIndex != -1)
                    returnedItems.add(userTodoItems.get(returnedIndex));
                break;

            case Priority:
                returnedItems = getItemsByPriority(Priority.valueOf(searchValue), userTodoItems);
                break;

            case StartDate:
                try {
                    Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchValue);
                    returnedItems = getItemsByStartDate(startDate, userTodoItems);
                } catch (ParseException e) {
                    System.out.println(font.ANSI_RED + "invalid date format" + font.ANSI_RESET);
                    return null;
                }
                break;

            case EndDate:
                try {
                    Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(searchValue);
                    returnedItems = getItemsByEndDate(endDate, userTodoItems);
                } catch (ParseException e) {
                    System.out.println(font.ANSI_RED + "invalid date format" + font.ANSI_RESET);
                    return null;
                }
                break;

            case Favorite:
                returnedItems = getItemsByFavorite(userTodoItems);
                break;
        }

        if (returnedItems.isEmpty()) {
            System.err.println("No results found.");
        } else {
            returnedItems.forEach(System.out::println);
        }
        return returnedItems;
    }
    public boolean addItemToFavorite(String name,String title){
        boolean updated = repository.addItemToFavorite(name,title);
        /*if(updated) {
            //needs to be added to client
            int itemIndex = getItemByTitle(title, userTodoItems);
            userTodoItems.get(itemIndex).setFavorite(true);
            System.out.println("ADDED TO FAVORITES SUCCESSFULLY");
        }*/
        return updated;
    }

    public void printFavorites(ArrayList<TodoItem> userTodoItems) {
        searchShowItemsBySearchKey(SearchKey.Favorite, "true", userTodoItems);
    }

    public boolean addItemToCategory(String name,String title, Category category){
        boolean updated = repository.addItemToCategory(name,title,category);
        /*if(updated) {
            int itemIndex = getItemByTitle(title,userTodoItems);
            userTodoItems.get(itemIndex).setCategory(category);
            System.out.println("ADDED TO CATEGORY SUCCESSFULLY");
        }*/
        return updated;
    }



}
