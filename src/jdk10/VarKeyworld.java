package jdk10;

import java.util.List;

public class VarKeyworld {
    public static void main(String[] args) {
        String text = "Hello Java 10";
        var textVar = "Hello Java 10";
        var list = List.of("A","B","C");
        System.out.println(list);
    }
}
