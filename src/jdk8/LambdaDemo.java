package jdk8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LambdaDemo {

    public static void main(String[] args) {
        /**

         // https://pdai.tech/md/java/java8/java8-stream.html

         // age > 25, female,top3 by name
         javaProgrammers.stream()
         .filter((p) -> (p.getAge() > 25))
         .filter((p) -> ("female".equals(p.getGender())))
         .sorted((p, p2) -> (p.getFirstName().compareTo(p2.getFirstName())))
         .limit(3)
         //.forEach(e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary())) // salary
         .forEach((p) -> System.out.printf("%s %s; ", p.getFirstName(), p.getLastName()));

         // max salary
         Person person = javaProgrammers
         .stream()
         .max((p, p2) -> (p.getSalary() - p2.getSalary()))
         .get()

         // first name store in TreeSet
         TreeSet<String> javaDevLastName = javaProgrammers
         .stream()
         .map(Person::getLastName)
         .collect(toCollection(TreeSet::new))

         // all Java program money
         int totalSalary = javaProgrammers
         .parallelStream()
         .mapToInt(p -> p.getSalary())
         .sum();



         */
        List<Person> personList = getTestList();
        personList.sort(Comparator.comparing(Person::getName, String.CASE_INSENSITIVE_ORDER)
                .thenComparing(Person::getId, (a, b) -> b.compareTo(a))
                .thenComparingInt(Person::getAge));
        personList.stream().forEach(System.out::println);

        System.out.println(String.join(":", "foobar", "foo", "bar")); //foobar:foo:bar
        System.out.println("foobar:foo:bar"
                .chars()
                .distinct()
                .mapToObj(c -> String.valueOf((char)c))
                .sorted()
                .collect(Collectors.joining())); // :abfor

        // bar:foobar
        Pattern.compile(":")
                .splitAsStream("foobar:foo:bar")
                .filter(s -> s.contains("bar"))
                .sorted()
                .collect(Collectors.joining(":"));

        //  1
        Pattern pattern = Pattern.compile(".*@gmail\\.com");
        Stream.of("bob@gmail.com", "alice@hotmail.com")
                .filter(pattern.asPredicate())
                .count();

    }

    public static List<Person> getTestList() {

        List<Person> persons = new ArrayList<Person>();
        persons.add(new Person(1L, "301", 17));
        persons.add(new Person(2L, "302", 27));
        persons.add(new Person(3L, "303", 37));
        persons.add(new Person(4L, "304", 47));
        persons.add(new Person(5L, "305", 57));
        persons.add(new Person(6L, "306", 67));
        persons.add(new Person(7L, "307", 77));
        persons.add(new Person(8L, "308", 87));

        return persons;
    }

}
