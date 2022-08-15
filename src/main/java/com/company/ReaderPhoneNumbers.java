package com.company;

import java.io.*;

class ReaderPhoneNumbers {
    private static final String RELATIVE_PATH = "src/main/resources/file.txt";

    public static void main(String[] args) {
        File file = new File(RELATIVE_PATH);

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String phoneNumber = reader.readLine();
            while (phoneNumber != null) {
                if (phoneNumber.matches("\\d{3}\\-\\d{3}\\-\\d{4}") | phoneNumber.matches("\\(\\d{3}\\)\\s\\d{3}\\-\\d{4}")) {
                    System.out.println(phoneNumber);
                }
                phoneNumber = reader.readLine();
            }
        } catch (IOException exc) {
            System.err.print(exc.getMessage());
        }
    }
}
