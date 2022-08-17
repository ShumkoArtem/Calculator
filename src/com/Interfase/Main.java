package com.Interfase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static String menu;
    public static ArrayList<Double> numbersArray = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        firstMenu();
        switchFunction();

    }

    private static void switchFunction() throws IOException {
        if (menu.equals("1")) {
            System.out.println("Введите арифметическое выражение содержащее числа int или double и знаки + - * /");
            String arithmeticExpression = reader.readLine();

            checkArithmeticExpression(arithmeticExpression);
            firstMenu();
            switchFunction();

        } else if (menu.equals("2")) {
            System.out.println("exid");
        } else {
            System.out.println("Неверно выбран пункт из меню");
            firstMenu();
            switchFunction();
        }
    }

    private static void firstMenu() throws IOException {
        System.out.println("1 - считать \n2 - выйти");
        menu = reader.readLine();
    }

    private static void checkArithmeticExpression(String arithmeticExpression) throws IOException {
        char[] charArray = arithmeticExpression.toCharArray();

        boolean flag = false;
        if (Character.isDigit(charArray[0])) {
            for (int i = 1; i < charArray.length; i++) {
                if (Character.isDigit(charArray[i]) || charArray[i] == '+' || charArray[i] == '-'
                        || charArray[i] == '*' || charArray[i] == '/' || i == '.') {
                    flag = true;
                } else {
                    System.out.println("Вы ввели неверное выражение");
                    flag = false;
                    firstMenu();
                    switchFunction();
                    break;
                }
            }
        }else{
            System.out.println("Вы ввели неверное выражение");
            flag = false;
            firstMenu();
            switchFunction();
        }
        System.out.println(arithmeticExpression);

        //тут будет рассчет выражения
        if (flag == true) {
            calculation(charArray);
        }
    }

    private static void calculation(char[] charArray) {
        StringBuilder temp = new StringBuilder();
        int count = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isDigit(charArray[i]) || charArray[i] == '.') {
                temp.append(charArray[i]);
            } else {
                numbersArray.add(Double.parseDouble(temp.toString()));
                count++;
                temp.setLength(0);
            }

        }
        numbersArray.add(Double.parseDouble(temp.toString()));

        System.out.println(numbersArray);
    }
}
