package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class textForStat {
    static int key = 0;
    static String russianAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    static String englishAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String punctuationMarks = "\r\n.?!,;: -()\"";
    static String numbers = "1234567890";
    static String symbols = russianAlphabet + englishAlphabet + punctuationMarks + numbers;
    static char[] chars = symbols.toCharArray();

    public static void fileAddressRequestForStats() throws IOException {
        System.out.println("Введите адрес файл для сбора статистики. Допустимый формат: txt");
        Scanner scanner = new Scanner(System.in);
        Path pathFileForStats = Path.of(scanner.nextLine());
        String lineForStats = Files.readString(pathFileForStats);
        formatСheck(pathFileForStats);
        ArrayList<String> strings = statAnalysis(lineForStats);

        System.out.println("Введите адрес файла для расшифровки. Допустимый формат: txt");
        Path pathFileForDecr = Path.of(scanner.nextLine());
        String lineForDecr = Files.readString(pathFileForDecr);
        formatСheck(pathFileForDecr);
        ArrayList<String> strings2 = statAnalysis(lineForDecr);
        characterReplacement(strings, strings2, lineForDecr);
        String data = characterReplacement(strings, strings2, lineForDecr);
        writeFile(data);
        System.out.println("Конец");
    }

    private static void writeFile(String data) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дерикторию для записи. Домустимый формат: txt");
        String str = scanner.nextLine();
        Path check = Path.of(str);
        formatСheck(check);
        Files.createFile(check);
        Files.writeString(check, data);
    }

    private  static String characterReplacement( ArrayList<String> strings,  ArrayList<String> strings2, String str){
        String s = str.toLowerCase();
        StringBuilder sbr = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            for (int j = 0; j < strings2.size(); j++) {
                if (c == strings2.get(j).charAt(0)){
                    sbr.append(strings.get(j).charAt(0));
                    break;
                }
            }
        }
        return sbr.toString();
    }

    private static ArrayList<String> statAnalysis(String line) {
        char[] arrline;
        int count = 1;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<String> list = new ArrayList<String>();
        arrline = line.toLowerCase().toCharArray();
        for (int i = 0; i < arrline.length; i++) {
            list.add(String.valueOf(arrline[i]));
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = i + 1; j < list.size(); j++) {
                if (list.get(i).equals(list.get(j))) {
                    count++;
                    list.remove(j);
                    j--;
                }
            }
            map.put(list.get(i), count);
            count = 1;
        }
        LinkedHashMap<String, Integer> collect = map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(LinkedHashMap::new, (m, c) -> m.put(c.getKey(), c.getValue()),
                        LinkedHashMap::putAll);
        ArrayList<String> strings = new ArrayList<>(collect.keySet());

        return strings;
    }

    private static void formatСheck(Path format) {
        String check = format.toString();
        if (check.endsWith(".txt")) {
            System.out.println("Формат введен корректно");
        } else {
            System.out.println("Формат введен не верно!");
        }
    }
}
