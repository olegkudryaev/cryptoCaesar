import java.io.IOException;
import java.util.Scanner;

import static test.textEncryption.*;
import static test.textDecryption.*;
import static test.bruteForce.*;
import static test.textForStat.*;

public class controller {

    public static void main(String[] args) throws IOException {

        Scanner consoleText = new Scanner(System.in);
        System.out.println("Здравствуйте! Вас приветствует крипто Цезарь. \n" +
                "Задача этой программы в шифровании и дешифровании текстов различными методами\n" +
                "1 - Шифрование текста по методу Цезаря\n" +
                "2 - Расшифрование текста по методу Цезаря\n" +
                "3 - Криптоанализ текста (далее необходимо выбрать метод криптоанализа)\n" +
                "A - метод Brute force(поиск грубой силы)\n" +
                "B - метод статистического анализа\n" +
                "exit - прекращение работы программы");
        System.out.println();
        System.out.println("Вам необходимо выбрать метод");

        while (consoleText.hasNextLine()) {
            String question = consoleText.nextLine();
            if (question.equals("1")) {
                fileAddressRequestForEncryption();
                break;
            } else if (question.equals("2")) {
                fileAddressRequestForDecryption();
                break;
            } else if (question.equals("3")) {
                System.out.println("Вы выбрали криптоанализ текста\n" +
                        "Необходимо выбрать какой метод необходимо испаользовать?\n" +
                        "A - метод Brute force(поиск грубой силы)\n" +
                        "B - метод статистического анализа");
                String choiceOfMethod = consoleText.nextLine();
                if (choiceOfMethod.equals("A")) {
                    System.out.println("Вы выбрали метод Brute force(поиск грубой силы)");
                    fileAddressRequestForBruteForce();
                    break;
                } else if (choiceOfMethod.equals("B")) {
                    System.out.println("Вы выбрали метод статистического анализа");
                    fileAddressRequestForStats();
                    break;
                } else {
                    System.out.println("Вы ввели не допустимые значения");
                }
            }
            else if (question.equals("exit")){
                break;
            }
            System.out.println("Вы ввели не допустимые значения");

        }
    }
}
