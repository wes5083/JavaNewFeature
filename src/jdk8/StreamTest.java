package jdk8;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {

        /** Anonymous class **/
        new Thread(() -> System.out.println("In Java8, Lambda expression rocks !!")).start();


        /** for each **/
        List features = Arrays.asList("Lambdas", "Default Method", "Stream API", "Date and Time API");
        features.forEach(n -> System.out.println(n));
        features.forEach(System.out::println);

        /** construction reference **/
//        Supplier<Student> s = () -> new Student();
//        Supplier<Student> s = Student::new;

        /** Object: instance method **/
//        set.forEach(t -> System.out.println(t));
//        set.forEach(System.out::println);

        /** static method **/
//        Stream<Double> stream = Stream.generate(() -> Math.random());
        Stream<Double> stream = Stream.generate(Math::random);

        /** ClassName: instance method **/
//        TreeSet<String> set = new TreeSet<>((s1,s2) -> s1.compareTo(s2));
        TreeSet<String> set = new TreeSet<>(String::compareTo);


        /** Filter & Predicate **/
        List<String> languages = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "", "JavaScript");

        System.out.println("Languages which starts with J :");
        filter(languages, (str) -> str.toString().startsWith("J"));

        System.out.println("Languages which ends with a ");
        filter(languages, (str) -> str.toString().endsWith("a"));

        System.out.println("Print all languages :");
        filter(languages, (str) -> true);

        System.out.println("Print no language : ");
        filter(languages, (str) -> false);

        System.out.println("Print language whose length greater than 4:");
        filter(languages, (str) -> str.toString().length() > 4);


        /** Filter & Predicate **/
        Predicate<String> startsWithJ = (n) -> n.startsWith("J");
        Predicate<String> fourLetterLong = (n) -> n.length() == 4;
        languages.stream()
                .filter(startsWithJ.and(fourLetterLong))
                .forEach((n) -> System.out.print("nName, which starts with 'J' and four letter long is : " + n));


        /** Map&Reduce **/
        List<Integer> costBeforeTax = Arrays.asList(100, 200, 300, 400, 500);
        double bill = costBeforeTax.stream().map(cost -> cost + 0.12 * cost).reduce((sum, cost) -> sum + cost).get();
//        double bill = costBeforeTax.stream().mapToDouble( cost-> cost + 0.12 * cost).reduce((sum, cost) -> sum + cost).get();
        System.out.println("Total : " + bill);

        /** Collectors **/
        List<String> G7 = Arrays.asList("USA", "Japan", "France", "Germany", "Italy", "U.K.", "Canada");
        String G7Countries = G7.stream().map(x -> x.toUpperCase()).collect(Collectors.joining(", "));
        System.out.println(G7Countries);

//        Collectors.joining(", ")
//        Collectors.toList()
//        Collectors.toSet()
//        Collectors.toMap(MemberModel::getUid, Function.identity())
//        Collectors.toMap(ImageModel::getAid, o -> IMAGE_ADDRESS_PREFIX + o.getUrl())

        /** flatMap **/
        List<Integer> result = Stream.of(Arrays.asList(1, 3), Arrays.asList(5, 6)).flatMap(a -> a.stream()).collect(Collectors.toList());
        // result [1, 3, 5, 6]

        /** distinct **/
//        List<LikeDO> likeDOs=new ArrayList<LikeDO>();
//        List<Long> likeTidList = likeDOs.stream().map(LikeDO::getTid)
//                .distinct().collect(Collectors.toList());

        /** count **/
        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person(1L, "p1", 17));
        persons.add(new Person(2L, "p2", 27));
        persons.add(new Person(3L, "p3", 37));
        persons.add(new Person(4L, "p4", 47));
        long countOfAdult = persons.stream().filter(p -> p.getAge() > 18).count();
        System.out.println("----------countOfAdult----------" + countOfAdult);

        /** Match **/
        List<String> test = Arrays.asList("Java", "Scala", "C++", "Haskell", "Lisp", "", "JavaScript");
        boolean anyStartsWithA = test.stream().anyMatch((s) -> s.startsWith("a"));
        System.out.println("------anyStartsWithA---" + anyStartsWithA);      // false

        boolean allStartsWithA = test.stream().allMatch((s) -> s.startsWith("a"));
        System.out.println("----allStartsWithA-----" + allStartsWithA);      // false

        boolean noneStartsWithZ = test.stream().noneMatch((s) -> s.startsWith("z"));
        System.out.println("----noneStartsWithZ-----" + noneStartsWithZ);      // true


        /** summary Statistics **/
        Person a = persons.stream().max(Comparator.comparing(t -> t.getId())).get();
        System.out.println(a.getId());

        /** multile compare **/
        Person person = persons.stream().min(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if (o1.getId() > o2.getId()) return -1;
                if (o1.getId() < o2.getId()) return 1;
                return 0;
            }
        }).get();
        List<Integer> primes = Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29);
        IntSummaryStatistics stats = primes.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("Highest prime number in List : " + stats.getMax());
        System.out.println("Lowest prime number in List : " + stats.getMin());
        System.out.println("Sum of all prime numbers : " + stats.getSum());
        System.out.println("Average of all prime numbers : " + stats.getAverage());

        /** peek **/
        System.out.println(persons);

        List<Person> list2 = persons.stream()
                .filter(f -> f.getName().startsWith("p"))
                .peek(t -> {
                    System.out.println(t.getName());
                })
                .collect(Collectors.toList());
        System.out.println("------peek------" + list2);


        /**  **/


        /**  **/


        /**  **/


        /**  **/

    }


    public static void filter(List names, Predicate condition) {
        names.stream().filter((name) -> (condition.test(name))).forEach((name) -> {
            System.out.println(name + " ");
        });
    }


}
