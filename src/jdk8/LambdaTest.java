package jdk8;

import java.util.Arrays;

public class LambdaTest {

    final static String salutation = "Hello";

    public static void main(String[] args) {
        LambdaTest lambda = new LambdaTest();

        MathOperation addition = (int a, int b) -> a + b;

        MathOperation subtraction = (a, b) -> a - b;

        MathOperation multiplication = (int a, int b) -> {
            return a * b;
        };

        MathOperation division = (int a, int b) -> a / b;

        System.out.println("10 + 5 = " + lambda.operate(10, 5, addition));
        System.out.println("10 - 5 = " + lambda.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + lambda.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + lambda.operate(10, 5, division));

        GreetingService greetService = message -> System.out.println("message: " + message);
        greetService.sayMessage("Hello");
        greetService.sayMessage("World");

        GreetingService greetService1 = message -> System.out.println(salutation + message);
        greetService1.sayMessage(" Message ");

        final int num = 1;
        Converter<Integer, String> s = (param) -> System.out.println(String.valueOf(param + num));
        s.convert(2);  // 3

        String[] array = new String[] { "Apple", "Orange", "Banana", "Lemon" };
        Arrays.sort(array, (s1, s2) -> {
            return s1.compareTo(s2);
        });
        System.out.println(String.join(", ", array));

    }

    interface MathOperation {
        int operation(int a, int b);
    }

    interface GreetingService {
        void sayMessage(String message);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

    public interface Converter<T1, T2> {
        void convert(int i);
    }
}
