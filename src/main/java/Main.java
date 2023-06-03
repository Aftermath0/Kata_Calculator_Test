import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static String x;
    static String y;
    static boolean romanianDetector = false;
    static String operator;
    public static String calc(String input) throws IOException {
        input = input.trim();

        String pattern = "^(\\w+)\\s*(\\W)\\s*(\\w+)";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(input.toUpperCase());
        if(m.matches()){
            if(m.group(2).matches("[-+*/]")){
                operator = m.group(2);
            } else throw new IOException("строка не является математической операцией данного калькулятора");
            if((m.group(1).matches("[1-9]|10|") && m.group(3).matches("[1-9]|10|")) || (m.group(1).matches("IX|IV|V?I{0,3}|X|") && m.group(3).matches("IX|IV|V?I{0,3}|X|")) ){
                if(m.group(1).matches("IX|IV|V?I{0,3}|X|") && m.group(3).matches("IX|IV|V?I{0,3}|X|")){
                    romanianDetector = true;
                }
                x = m.group(1);
                y = m.group(3);

            }else if((m.group(1).matches("[1-9]|10|") && m.group(3).matches("IX|IV|V?I{0,3}|X|") ) || (m.group(1).matches("IX|IV|V?I{0,3}|X|") && m.group(3).matches("[1-9]|10|"))){

                throw new IOException("Ошибка! Используются одновременно разные системы счисления");

            } else throw new IOException("Ошибка! Формат математической операции не удовлетворяет заданию - два операнда (римские или арабские числа от 1 до 10) и один оператор (+, -, /, *)");

        } else throw new IOException("Ошибка! Строка не является математической операцией данного калькулятора");

        //Производим операции над числами и выдаем ответ
        Converter c = new Converter();
        Calculator calculator = new Calculator();
        if(romanianDetector){
            return c.arabicToRoman(calculator.Calculation(c.romanToArabic(x),c.romanToArabic(y),operator,romanianDetector));
        } else {
            return String.valueOf(calculator.Calculation(Integer.parseInt(x),Integer.parseInt(y),operator,romanianDetector));
        }
    }

    public static void main(String[] args) throws IOException {

        System.out.println("Ведите строку формата X оператор Y , где X и Y это целые римские или арабские числа от 1 до 10 и оператор (+, -, *, /)");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        System.out.println(calc(s));


    }
}
