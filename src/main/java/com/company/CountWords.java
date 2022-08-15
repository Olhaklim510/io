package com.company;

import java.io.*;
import java.util.*;

class CountWords {
    private static final String RELATIVE_PATH = "src/main/resources/words.txt";

    public static void main(String[] args) {
        File file = new File(RELATIVE_PATH);
        HashMap<String, Integer> hashMap = new HashMap<>();
        Integer count = null;
        ArrayList<String> arrayWords = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            String word = bufferedReader.readLine();
            while (word != null) {
                arrayWords.addAll(List.of(word.split(" ")));
                word = bufferedReader.readLine();
            }

            for (String str : arrayWords) {
                count = hashMap.get(str);
                if (count == null) {
                    hashMap.put(str, 1);
                } else {
                    hashMap.put(str, count + 1);
                }
            }

            ArrayList<Map.Entry<String, Integer>> list = new ArrayList<>(hashMap.entrySet());
            list.sort(new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                    return o2.getValue() - o1.getValue();
                }
            });

            for (int i=0;i< list.size();i++) {
                System.out.println(list.get(i).getKey()+" "+list.get(i).getValue());
            }

        } catch (IOException exc) {
            System.err.print(exc.getMessage());
        }
    }
}
