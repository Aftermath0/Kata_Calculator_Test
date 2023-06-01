import java.util.HashMap;
import java.util.Objects;

class Converter {
    HashMap<Character,Integer> romanToArabic = new HashMap<>();
    HashMap<Integer,String> arabicToRoman = new HashMap<>();
    Converter(){
        romanToArabic.put('I',1);
        romanToArabic.put('V',5);
        romanToArabic.put('X',10);
        romanToArabic.put('L',50);
        romanToArabic.put('C',100);

        arabicToRoman.put(1,"I");
        arabicToRoman.put(5,"V");
        arabicToRoman.put(10,"X");
        arabicToRoman.put(50,"L");
        arabicToRoman.put(100,"C");
    }
    // Перевод из римских в арабские
    public int romanToArabic(String s){
        int result = 0;
        StringBuilder sb;
        sb = new StringBuilder(s);
        do {
            if(s.length() >=2 && romanToArabic.get(s.charAt(0)) <romanToArabic.get(s.charAt(1))){
                result = romanToArabic.get(s.charAt(1)) - romanToArabic.get(s.charAt(0));
                s = sb.deleteCharAt(0).deleteCharAt(0).toString();

            }else{result += romanToArabic.get(s.charAt(0));
                s = sb.deleteCharAt(0).toString();}



        }while(!Objects.equals(s, ""));

        return result;
    }

    // Перевод из арабских в римские
    public String arabicToRoman(int num){


        StringBuilder result = new StringBuilder();
        int i = 0;
        int[] keyArray = new int[arabicToRoman.size()];
            for( int key : arabicToRoman.keySet()){                 // Создаем массив ключей для поиска максимально близкого числа к заданому
                keyArray[i] = key;
                i++;
            }
            boolean sorted = false;
            int temp;
            while(!sorted) {
                sorted = true;
                for (int a = 0; a < keyArray.length - 1; a++) {                             // Сортируем массив повозрастанию
                    if (keyArray[a] > keyArray[a +1]) {
                        temp = keyArray[a];
                        keyArray[a] = keyArray[a +1];
                        keyArray[a +1] = temp;
                        sorted = false;
                    }
                }
            }



            int rem = 0;
           if (num >10){                                               // Из-за
               rem = num % 10;
               num -= rem;
           }
            do {
                if(arabicToRoman.containsKey(num)){
                    result.append(arabicToRoman.get(num));
                    num = rem;
                    rem = 0;

                }else{
                    for (int j = 0; j < keyArray.length; j++) {
                        if(num > keyArray[j] && num < keyArray[j+1]){
                                boolean checked = false;
                                int v = 0;
                                for (int value : keyArray) {
                                    if (num + value == keyArray[j + 1]) {
                                        checked = true;
                                        v = value;
                                    }
                                }
                                if(checked){
                                     result.append(arabicToRoman.get(v)).append(arabicToRoman.get(keyArray[j+1]));
                                     num = rem;
                                     rem = 0;
                                }else {
                                    num -= keyArray[j];
                                    result.append(arabicToRoman.get(keyArray[j]));
                                }
                        }
                    }
                }

            }while (num != 0 );



        return result.toString();
    }






}
