//Завдання 1
//        Програма повинна містити методи для реалізації наступного функціоналу:
//
//        створення нового об'єкта в https://jsonplaceholder.typicode.com/users. +
//        Можливо, ви не побачите одразу змін на сайті.
//        Метод працює правильно, якщо у відповідь на JSON з об'єктом повернувся такий самий JSON,
//        але зі значенням id більшим на 1, ніж найбільший id на сайті.
//
//        оновлення об'єкту в https://jsonplaceholder.typicode.com/users. +
//        Можливо, ви не побачите одразу змін на сайті.
//        Вважаємо, що метод працює правильно, якщо у відповідь ви отримаєте оновлений JSON
//        (він повинен бути таким самим, що ви відправили).
//
//        видалення об'єкта з https://jsonplaceholder.typicode.com/users. +
//        Тут будемо вважати коректним результат - статус відповіді з групи 2xx (наприклад, 200).
//
//        отримання інформації про всіх користувачів https://jsonplaceholder.typicode.com/users +
//
//        отримання інформації про користувача за id https://jsonplaceholder.typicode.com/users/{id} +
//
//        отримання інформації про користувача за username - https://jsonplaceholder.typicode.com/users?username={username} +

public class Main {
    public static void main(String[] args) {

        //Завдання 1
        String jsonInput = "{"
                + "\"name\": \"Dmytro\","
                + "\"username\": \"Shvalikovskyi\","
                + "\"email\": \"dmytro.shvalikovskyi.ki.2020@lpnu.ua\""
                + "}";

        String createdUser = JsonPlaceholderAPI.createObj(jsonInput);
        System.out.println("\nСтворено нового користувача: \n" + createdUser);
        String updatedObj = "{"
                + "\"name\": \"Updated name\","
                + "\"username\": \"Updated username\","
                + "\"email\": \"updatedEmail@gmail.com\""
                + "}";
        int userId = 10;
        String username = "Antonette";
        String updatedUser = JsonPlaceholderAPI.updateObj(userId, updatedObj);
        System.out.println("\nОновлений користувач: \n" + updatedUser);
        System.out.println(String.format("\nРезультат видалення об'єкта з id %d: %b", userId, JsonPlaceholderAPI.deleteObj(userId)));
        System.out.println(String.format("\nІнформація про користувачів:\n %s", JsonPlaceholderAPI.getUsersInfo()));
        System.out.println(String.format("Інформація про користувача з id %d:\n %s", userId, JsonPlaceholderAPI.getUserInfo(userId)));
        System.out.println(String.format("Інформація про користувача за username [%s]:\n %s", username, JsonPlaceholderAPI.getUserInfo(username)));

        //Завдання 2
        System.out.println(String.format("Перевірка завдання 2\nВивід коментарів до останнього поста користувача з id %d", userId));
        JsonPlaceholderAPI.printAllComments(userId);

        //Завдання 3
        System.out.println(String.format("Перевірка завдання 3\nВивід невиконаних завдань користувача з id %d", userId));
        JsonPlaceholderAPI.openUserTasks(userId);
    }
}