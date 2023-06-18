package jdk9;

import java.util.List;
import java.util.stream.Collectors;

public class StreamImprove {

    public static void main(String[] args) {
        List<String> alphabets1 = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i");
        List<String> subset1 = alphabets1
                .stream()
                .takeWhile(s -> !s.equals("d"))
                .collect(Collectors.toList());
        //print：[a, b, c]
        System.out.println(subset1);

        List<String> alphabets2 = List.of("a", "b", "c", "d", "e", "f", "g", "h", "i");
        List<String> subset2 = alphabets2
                .stream()
                .dropWhile(s -> !s.equals("d"))
                .collect(Collectors.toList());
        //print：[d, e, f, g, h, i]
        System.out.println(subset2);
    }
}
