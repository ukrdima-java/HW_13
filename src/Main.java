//Завдання 1
//        Програма повинна містити методи для реалізації наступного функціоналу:
//
//        створення нового об'єкта в https://jsonplaceholder.typicode.com/users.
//        Можливо, ви не побачите одразу змін на сайті.
//        Метод працює правильно, якщо у відповідь на JSON з об'єктом повернувся такий самий JSON,
//        але зі значенням id більшим на 1, ніж найбільший id на сайті.
//
//        оновлення об'єкту в https://jsonplaceholder.typicode.com/users.
//        Можливо, ви не побачите одразу змін на сайті.
//        Вважаємо, що метод працює правильно, якщо у відповідь ви отримаєте оновлений JSON
//        (він повинен бути таким самим, що ви відправили).
//
//        видалення об'єкта з https://jsonplaceholder.typicode.com/users.
//        Тут будемо вважати коректним результат - статус відповіді з групи 2xx (наприклад, 200).
//
//        отримання інформації про всіх користувачів https://jsonplaceholder.typicode.com/users
//
//        отримання інформації про користувача за id https://jsonplaceholder.typicode.com/users/{id}
//
//        отримання інформації про користувача за username - https://jsonplaceholder.typicode.com/users?username={username}

public class Main {
    public static void main(String[] args) {
        //Завдання 1
        JsonPlaceholderAPI.createObj();
        JsonPlaceholderAPI.updateObj();
        JsonPlaceholderAPI.deleteObj();
        JsonPlaceholderAPI.getUsersInfo();
        JsonPlaceholderAPI.getUserInfo(1);
        JsonPlaceholderAPI.getUserInfo("username");
        //Завдання 2
        JsonPlaceholderAPI.printAllComments(1);
        //Завдання 3
        JsonPlaceholderAPI.openUserTasks(1);
    }
}