package jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LambdaTestNew {

    public static void main(String[] args) {
        List<String> listProgram = Arrays.asList("java", "c#", "javascript");
        //before java8
        for (String str : listProgram) {
            System.out.println("before java8," + str);
        }
        //after java8
        listProgram.forEach(x -> System.out.println("after java8," + x));


        List<Double> list = Arrays.asList(10.0, 20.0, 30.0);
        list.stream().map(x -> x + x * 0.05).forEach(x -> System.out.println(x));


        //before java8
        List<Double> listCostBefore = Arrays.asList(10.0, 20.0, 30.0);
        double sum = 0;
        for (double each : listCostBefore) {
            each += each * 0.05;
            sum += each;
        }
        System.out.println("before java8, " + sum);
        //after java8
        List<Double> listCostAfter = Arrays.asList(10.0, 20.0, 30.0);
        double sum2 = list.stream().map(x -> x + x * 0.05).reduce((sum1, x) -> sum1 + x).get();
        System.out.println("after java8, " + sum2);


        List<Double> cost = Arrays.asList(10.0, 20.0, 30.0, 40.0);
        List<Double> filteredCost = cost.stream().filter(x -> x > 25.0).collect(Collectors.toList());
        filteredCost.forEach(x -> System.out.println(x));


        List<String> languages = Arrays.asList("Java", "Python", "scala", "Shell", "R");
        filterTest(languages, x -> x.startsWith("J"));//Java
        filterTest(languages, x -> x.endsWith("a"));//Java,scala
        filterTest(languages, x -> true);//Java,Python,scala,Shell,R
        filterTest(languages, x -> false);//
        filterTest(languages, x -> x.length() > 4);//Python,scala,Shell,
    }


    public static void filterTest(List<String> languages, Predicate<String> condition) {
        languages.stream().filter(x -> condition.test(x)).forEach(x -> System.out.println(x + " "));
    }
}
