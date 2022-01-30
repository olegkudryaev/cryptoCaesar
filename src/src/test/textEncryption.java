package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class textEncryption {
    static int key = 0;
    static String russianAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    static String englishAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String punctuationMarks = "\r\n.?!,;: -()\"";
    static String numbers = "1234567890";
    static String symbols = russianAlphabet + englishAlphabet + punctuationMarks + numbers;
    static char[] chars = symbols.toCharArray();

    public static void fileAddressRequestForEncryption() throws IOException {
        System.out.println("Введите адрес файл. Допустимый формат: txt");
        Scanner scanner = new Scanner(System.in);
        Path pathFile = Path.of(scanner.nextLine());
        System.out.println("Введите ключ");
        key = scanner.nextInt();
        formatСheck(pathFile);
        fileСopy(pathFile);
        System.out.println("Конец");
    }

    private static void formatСheck(Path format) {
        String check = format.toString();
        if (check.endsWith(".txt")) {
            System.out.println("Формат введен корректно");
        } else {
            System.out.println("Формат введен не верно!");
        }
    }

    private static void fileСopy(Path pathFile) throws IOException {
        String str = Files.readString(pathFile);
        StringBuffer str2 = new StringBuffer(str);
        StringBuffer toWrite = encryption(str2);
        String text = toWrite.toString();
        writeFile(text);
    }

    private static StringBuffer encryption(StringBuffer text) {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < text.length(); i++) {
            char start = text.charAt(i);
            for (int j = 0; j < symbols.length(); j++) {
                char end = symbols.charAt(j);
                if (start == end) {
                    char finish = symbols.charAt((j + key)%text.length());
                    str.append(finish);
                }
            }
        }
        return str;
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
}
