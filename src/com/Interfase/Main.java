package com.Interfase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    //через свич нужно делать

    public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static String menu;
    public static LinkedList<Double> numbersList = new LinkedList<>();
    public static LinkedList<Character> singsList = new LinkedList<>();


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
                        || charArray[i] == '*' || charArray[i] == '/' || charArray[i] == '.') {
                    flag = true;
                } else {
                    System.out.println("Вы ввели неверное выражение");
//                    flag = false;
                    firstMenu();
                    switchFunction();
                    break;
                }
            }
        } else {
            System.out.println("Вы ввели неверное выражение");
//            flag = false;
            firstMenu();
            switchFunction();
        }
        System.out.println(arithmeticExpression);

        //тут будет рассчет выражения
        if (flag == true) {
            splittingAnExpression(charArray);
        }
    }

    private static void splittingAnExpression(char[] charArray) {
        numbersList.clear();
        singsList.clear();
        StringBuilder temp = new StringBuilder();

        //int count = 0;
        for (int i = 0; i < charArray.length; i++) {
            if (Character.isDigit(charArray[i]) || charArray[i] == '.') {
                temp.append(charArray[i]);
            } else {
                numbersList.add(Double.parseDouble(temp.toString()));
                //count++;
                temp.setLength(0);
            }
        }
        numbersList.add(Double.parseDouble(temp.toString()));
        temp.setLength(0);

        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == '+' || charArray[i] == '-' || charArray[i] == '*' || charArray[i] == '/') {
                singsList.add(charArray[i]);
            }
        }
        System.out.println(numbersList);
        System.out.println(singsList);
        calculation(numbersList, singsList);
    }

    private static void calculation(LinkedList<Double> numbersList, LinkedList<Character> singsList) {
        double temp;
//        LinkedList<double>
        for (int i = 0; i < singsList.size(); i++) {
            if (singsList.get(i) == '*') {
                temp = numbersList.get(i) * numbersList.get(i + 1);
                numbersList.set(i + 1, temp);
                numbersList.set(i, 0.0);
                singsList.set(i, ' ');
                System.out.println(numbersList);
            } else if (singsList.get(i) == '/') {
                temp = numbersList.get(i) / numbersList.get(i + 1);
                numbersList.set(i + 1, temp);
                numbersList.set(i, 0.0);
                singsList.set(i, ' ');
                System.out.println(numbersList);
            }
        }
        List<Double> n =  numbersList.stream()
                .filter(aDouble -> aDouble != 0.0)
                .collect(Collectors.toList());
        List<Character> ch = singsList.stream()
                        .filter(character -> character != ' ')
                                .collect(Collectors.toList());
        System.out.println(n);
        System.out.println(ch);
        for (int i = 0; i < ch.size(); i++) {
            if (ch.get(i) == '+') {
                temp = n.get(i) + n.get(i + 1);
                n.set(i + 1, temp);
                n.set(i, 0.0);
                System.out.println(n);
            } else if (ch.get(i) == '-') {
                temp = n.get(i) - n.get(i + 1);
                n.set(i + 1, temp);
                n.set(i, 0.0);
                System.out.println(n);
            }
        }
        System.out.println(n.get(n.size()-1));
    }
}

//2+5+9*2-2/4+6

