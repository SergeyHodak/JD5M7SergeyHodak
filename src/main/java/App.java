import storage.DatabaseInitService;

public class App {
    public static void main(String[] args) {
        new DatabaseInitService().initDb(); //викликали підключення/створення/підтягнення міграцій Бази Данних
    }
}