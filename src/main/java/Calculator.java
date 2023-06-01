 class Calculator {

    public int Calculation(int x, int y , String operand, boolean romanianDetector){
        int result = 0;

        if(romanianDetector){
            switch (operand) {
                case ("-") -> {
                    result = x - y;
                    if(result<=0){throw new ArithmeticException("Ошибка! В римской системе исчисления нет отрицательных чисел или нуля");}
                    return result ;
                }
                case ("+") -> {
                    return  x + y;
                }
                case ("*") -> {
                    return  x * y;
                }
                case ("/") -> {
                    result = x / y;
                    if(result<=0){ throw new ArithmeticException("Ошибка! В римской системе исчисления нет отрицательных чисел, дробных или нуля");}
                    if (x % y != 0) {
                        return  x % y;
                    }
                    return  x / y;
                }
            }
        }
        return switch (operand) {
            case ("-") -> x - y;
            case ("+") -> x + y;
            case ("*") -> x * y;
            case ("/") -> x % y;
            default -> result;
        };


    }
}
