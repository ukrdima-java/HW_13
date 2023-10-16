import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class JsonPlaceholderAPI {
    private static final String JSON_URL = "https://jsonplaceholder.typicode.com/users";
    public static String createObj(String jsonObj) {
        try {
            URL url = new URL(JSON_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            try(DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) { //Відправляємо дані на сервер
                dos.writeBytes(jsonObj);
                dos.flush();
            }
            int responseCode = connection.getResponseCode();
            if(responseCode == 201) { //Успішний запит на сервер
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder(); //Зберігаємо відповідь від сервера
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();
                return response.toString();
            }
            else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String updateObj(int userId, String updatedObj) {
        try {
            URL url = new URL(JSON_URL + "/" + userId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Встановлення методу запиту на PUT
            connection.setRequestMethod("PUT");
            // Встановлення заголовків для JSON-даних
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            // Увімкнення можливості відправки даних PUT
            connection.setDoOutput(true);
            // Надсилання оновлених JSON-даних
            try (DataOutputStream wr = new DataOutputStream(connection.getOutputStream())) {
                wr.writeBytes(updatedObj);
                wr.flush();
            }
            // Отримання відповіді від сервера
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                // Код 200 означає успішне оновлення об'єкта
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static boolean deleteObj(int userId) {
        try {
            URL url = new URL(JSON_URL + "/" + userId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();
            return (responseCode >= 200 && responseCode < 300);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static String getUsersInfo() {
        try {
            URL url = new URL(JSON_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            StringBuilder result = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
    public static String getUserInfo(int userId) {
        try {
            URL url = new URL(JSON_URL + "/" + userId);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            StringBuilder result = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }
    public static String getUserInfo(String username) {
        try {
            URL url = new URL(JSON_URL + "/?username=" + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            StringBuilder result = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line).append("\n");
                }
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error";
        }
    }


//        Завдання 2 +
//        Доповніть програму методом, що буде виводити всі коментарі до останнього поста певного користувача і записувати їх у файл.
//
//        https://jsonplaceholder.typicode.com/users/1/posts Останнім вважаємо пост з найбільшим id.
//
//        https://jsonplaceholder.typicode.com/posts/10/comments
//
//        Файл повинен називатись user-X-post-Y-comments.json, де Х - id користувача, Y - номер посту.

    public static void printAllComments(int userId) {
        try {
            URL postURL = new URL(JSON_URL + "/" + userId + "/posts/");
            HttpURLConnection connection = (HttpURLConnection) postURL.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            StringBuilder posts = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
                String line;
                while((line = reader.readLine()) != null) {
                    posts.append(line).append("\n");
                }
            }
            JsonParser parser = new JsonParser();
            JsonArray postsArray = parser.parse(posts.toString()).getAsJsonArray();
            if(postsArray.size() > 0) {
                int lastPostId = postsArray.get(postsArray.size() - 1).getAsJsonObject().get("id").getAsInt();
                URL commentsURL = new URL("https://jsonplaceholder.typicode.com/posts/" + lastPostId + "/comments");
                HttpURLConnection commentConnection = (HttpURLConnection) commentsURL.openConnection();
                commentConnection.setRequestMethod("GET");
                commentConnection.setDoInput(true);

                StringBuilder result = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(commentConnection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line).append("\n");
                    }
                }
                System.out.println("Коментарі до останнього посту: \n" + result);
                try (FileWriter fileWriter = new FileWriter("user-" + userId + "-post-" + lastPostId + "-comments.json")) {
                    fileWriter.write(result.toString());
                }
            }
            else {
                System.out.println("Пости відсутні");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    Завдання 3 +
//    Доповніть програму методом, що буде виводити всі відкриті задачі для користувача з ідентифікатором X.
//
//            https://jsonplaceholder.typicode.com/users/1/todos.
//
//    Відкритими вважаються всі задачі, у яких completed = false.

    public static void openUserTasks(int user_id) {
        try {
            URL url = new URL(JSON_URL + "/" + user_id + "/todos");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);

            StringBuilder todos = new StringBuilder();
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while((line = reader.readLine()) != null) {
                    todos.append(line).append("\n");
                }
            }
            JsonParser parser = new JsonParser();
            JsonArray tasks = parser.parse(todos.toString()).getAsJsonArray();
            for(int i = 0; i < tasks.size(); i++) {
                if(!tasks.get(i).getAsJsonObject().get("completed").getAsBoolean())
                    System.out.println(tasks.get(i));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
