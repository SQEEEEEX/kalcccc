import java.io.IOException;
import java.util.Scanner;






public class Main {

    public static String[] calc(String exp) {
        String[] data = exp.split(" ");
        if (data.length != 3) {
            Scanner scn1 = new Scanner(System.in);
            System.out.println("Неверный формат ввода данных. Введите выражение, разделяя каждый символ _пробелом_");
            exp = scn1.nextLine();
            return calc(exp);
        } else {
            return data;
        }
    }



    public static void main(String[] args) throws Exception {
        Converter converter = new Converter();
        String[] actions = {"+", "-", "/", "*"};
        String[] regexActions = {"\\+", "-", "/", "\\*"};
        Scanner scn = new Scanner(System.in);
        System.out.print("Калькулятор: ");
        String exp = scn.nextLine();
        int actionIndex=-1;
        for (int i = 0; i < actions.length; i++) {
            if(exp.contains(actions[i])){
                actionIndex = i;
                break;
            }
        }
        if(actionIndex==-1){
            throw new Exception ("throws Exception //т.к. строка не является математической операцией");
        }
        String[] data = exp.split(regexActions[actionIndex]);
        int[] oper = new int[data.length];
//        for (int i = 0; i < data.length; i++) oper[i] = Integer.parseInt(data[i]);
        for (int i = 2; i < oper.length; i++) {
            throw new Exception ("throws Exception //введено больше двух операндов");
        }
        data = calc(exp);
        if(converter.isRoman(data[0]) == converter.isRoman(data[2])){
            boolean isRoman = converter.isRoman(data[0]);
            int a = 0;
            int b = 0;
            if(isRoman){
                a = converter.romanToInt(data[0]);
                b = converter.romanToInt(data[2]);
            }else{
                a = Integer.parseInt(data[0]);
                b = Integer.parseInt(data[2]);
            }
            int result;
            switch (actions[actionIndex]){
                case "+":
                    result = a+b;
                    break;
                case "-":
                    result = a-b;
                    break;
                case "*":
                    result = a*b;
                    break;
                default:
                    result = a/b;
                    break;
            }
            try {
                if (isRoman) {
                    System.out.println("Ответ: " + converter.intToRoman(result));
                } else {
                    System.out.println("Ответ: " + result);
                }
            }catch(NullPointerException e) {
                throw new Exception ("//т.к. в римской системе нет отрицательных чисел");
            }
        }
        else{
            throw new Exception ("//т.к. используются одновременно разные системы счисления");
        }
    }
}