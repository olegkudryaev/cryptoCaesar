package test;

import static test.textEncryption.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class textDecryption {
    static int key = 0;
    static String russianAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    static String englishAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String punctuationMarks = "\r\n.?!,;: -()\"";
    static String numbers = "1234567890";
    static String symbols = russianAlphabet + englishAlphabet + punctuationMarks + numbers;
    static char[] chars = symbols.toCharArray();

    public static void fileAddressRequestForDecryption() throws IOException {
        System.out.println("Введите адрес файл. Допустимый формат: txt");
        Scanner scanner = new Scanner(System.in);
        Path pathFile = Path.of(scanner.nextLine());
        System.out.println("Введите ключ");
        key = scanner.nextInt();
        formatСheck(pathFile);
        fileСopy(pathFile);
        System.out.println("Конец");
    }

    static void formatСheck(Path format) {
        String check = format.toString();
        if (check.endsWith(".txt")) {
            System.out.println("Формат введен корректно");
        } else {
            System.out.println("Формат введен не верно!");
        }
    }

    static void fileСopy(Path pathFile) throws IOException {
        String str = Files.readString(pathFile);
        StringBuffer str2 = new StringBuffer(str);
        StringBuffer toWrite = decryption(str2);
        String text = toWrite.toString();
        writeFile(text);
    }

    static StringBuffer decryption(StringBuffer text) {
        String forMethod = text.toString();
        char [] start = forMethod.toCharArray();
        char [] result = new char[start.length];
        int keyForDecr = chars.length - key;
        for (int i = 0; i < start.length; i++) {
            char stCh = start[i];
            for (int j = 0; j < chars.length; j++) {
                char resCh = chars[j];
                if (stCh == resCh){
                    result[i] = chars[(j + keyForDecr)% chars.length];
                }
            }
        }
        StringBuffer str = new StringBuffer(new String(result));
        return str;
    }

    static void writeFile(String data) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите дерикторию для записи. Домустимый формат: txt");
        String str = scanner.nextLine();
        Path check = Path.of(str);
        formatСheck(check);
        Files.createFile(check);
        Files.writeString(check, data);
    }
}


