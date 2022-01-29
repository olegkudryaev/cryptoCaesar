package test;

import java.util.*;
import java.util.Map;
import java.util.HashMap;


public class testForStat {
    static String russianAlphabet = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    static String englishAlphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String punctuationMarks = ".?!,;: -()\"";
    static String numbers = "1234567890";
    static String symbols = russianAlphabet + englishAlphabet + punctuationMarks + numbers;
    static char[] chars = symbols.toCharArray();

    public static void main(String[] args) {
        String line = "Произвольная строка для проведения разбора";
        char[] arrline = new char[line.length()];
        int count = 1;
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<String> list = new ArrayList<String>();

        arrline = line.replace(" ", "").toLowerCase().toCharArray();
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


//        System.out.print("Индивидуальные буквы: ");
//        for (Map.Entry<String, Integer> mapE : map.entrySet()) {
//            System.out.print(mapE.getKey() + ", ");
//        }
        System.out.println("\nКоличество вхождений каждой буквы в заданой строке:");
        for (Map.Entry<String, Integer> mapE : map.entrySet()) {
            System.out.println("буква \"" + mapE.getKey() + "\" - " + mapE.getValue());
        }

//        map.entrySet().stream()
//                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
//                .forEach(System.out::println);

        //

        LinkedHashMap<String, Integer> collect = map.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .collect(LinkedHashMap::new, (m, c) -> m.put(c.getKey(), c.getValue()),
                        LinkedHashMap::putAll);
        System.out.println(collect.toString());
        ArrayList<String> strings = new ArrayList<>(collect.keySet());
        System.out.println(strings.toString());
    }
}


// https://i.imgur.com/pvIOg5n.jpeg

//    private static HashMap<Character, Character> getCharacterStatistics(HashMap<Character, Integer> decryptedTextStatistics
//            , HashMap<Character, Integer> generalStatistics) {
//        HashMap<Character, Character> result = new HashMap<>();
//        for (int i = 0; i < ALPHABET.length(); i++) {
//            char c = ALPHABET.charAt(i);
//            Integer characterStat = generalStatistics.get(c);
//            Character closestCharacterFromStatMap = getClosestCharacterFromStatMap(decryptedTextStatistics, characterStat);
//            result.put(closestCharacterFromStatMap, c);
//        }
//        return result;
//    }
//
//    private static Character getClosestCharacterFromStatMap(HashMap<Character, Integer> statMap, Integer value){
//        Integer minDelta = Integer.MAX_VALUE;
//        Character currentChar = 'c';
//        for (Map.Entry<Character, Integer> characterIntegerEntry : statMap.entrySet()) {
//            int delta = Math.abs(characterIntegerEntry.getValue() - value);
//            if (delta<minDelta){


