package com.company;

import com.company.model.User;
import com.google.gson.*;
import java.io.*;
import java.util.ArrayList;

class UsersFile {
    private static final String RELATIVE_PATH_FOR_READING = "src/main/resources/file_with_users_read.txt";
    private static final String RELATIVE_PATH_FOR_WRITING = "src/main/resources/file_with_users_write.txt";

    //reading
    public static void main(String[] args) {
        File fileRead = new File(RELATIVE_PATH_FOR_READING);
        ArrayList<User> listOfUsers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileRead))) {
            String result = reader.readLine();
            while (result != null) {
                result = reader.readLine();
                User user = new User();
                if (result == null) {
                    break;
                }
                String[] arreyUsers = result.split(" ");
                user.name = arreyUsers[0];
                user.age = Integer.valueOf(arreyUsers[1]);
                listOfUsers.add(user);
            }
        } catch (IOException exc) {
            System.err.print(exc.getMessage());
        }

    //writing
        File fileWrite = new File(RELATIVE_PATH_FOR_WRITING);
        checkIfFileAvailable(fileWrite);

        Gson gson=new GsonBuilder().setPrettyPrinting().create();
        String json=gson.toJson(listOfUsers);

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileWrite))) {
            bufferedWriter.write(json);
        } catch (IOException exc) {
            System.err.print(exc.getMessage());
        }
    }

    private static void checkIfFileAvailable(File file) {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try {
                file.createNewFile();
            } catch (IOException exc) {
                System.err.print(exc.getMessage());
            }
        }
    }
}
