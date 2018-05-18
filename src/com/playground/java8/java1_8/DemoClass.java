package com.playground.java8.java1_8;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class DemoClass {


    /**
     * Java 8 specific topics
     **/

    String resultString = "Unsorted:\n";


    public void lambdaSample() {

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");


        names.stream().forEach( //  stream
                (String name) -> resultString += name + "\n" // lambda
        );


        Collections.sort(names);


        resultString += "\n\nSorted:\n\n";

        names.stream().forEach((String name) -> resultString += name + "\n");  // lambda + stream


        // TODO: print output
        // sampleText.setText(resultString);

    }


    @FunctionalInterface
    public interface Converter<F, T> {
        T convert(F from);
    }


    @FunctionalInterface
    public interface Prefixer<F, T> {
        T addPrefix(F name, int FLAG);

        int MALE = 0;
        int FEMALE = 1;
    }

    // Lambda Signature: Whats actually defines a lambda under the hood
    public void funcInterfaceSample() {

        Converter<String, Integer> converterNumber = (from) -> Integer.valueOf(from);

        Integer converted = converterNumber.convert("123");

        // TODO: print output
        //Log.d(TAG, "funcInterfaceSample: String, Integer >> " + converted);    // 123


        Prefixer<String, String> prefixer = (name, FLAG) -> FLAG == Prefixer.MALE ? "Mr." + name : "Ms." + name;

        // TODO: print output
        //Log.d(TAG, "funcInterfaceSample: Prefixer: " + prefixer.addPrefix("Michael", Prefixer.MALE));


        // TODO: print output
        // Log.d(TAG, "funcInterfaceSample: Prefixer: " + prefixer.addPrefix("Sarah", Prefixer.FEMALE));

    }

    // Use case: A step towards functional programming
    public void methodReferencesSample() {
        // using :: to pass method or constructor as references

        // referenced method's parameter and return types should match specified generic types
        Converter<String, Integer> converter = Integer::valueOf; // Integer's valueOf() has INteger return type & String as parameter.
        Integer converted = converter.convert("355624");

        // TODO: print output
        // instance method references
        Something something = new Something();
        Converter<String, String> instanceRef = something::startsWith;
        String instanceResult = instanceRef.convert("Java");
        // TODO: print output

        PersonFactory<PersonName> personFactory = PersonName::new;  // passing constructor references
        PersonName personName = personFactory.create("Peter", "Parker");
        // TODO: print output
    }


    public interface PersonFactory<P extends PersonName> {
        P create(String firstName, String lastName);
    }





    public class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }


    }

    // Use case: Validations, chaining, factory, non-returning ops, comparators, optionals
    public void builtInFunctionalInterfacesSample() {

        /**
         * Predicates are boolean-valued functions of one argument.
         * The interface contains various default methods for composing
         * predicates to complex logical terms (and, or, negate)
         *
         */


        // Predicates
        Predicate<String> predicate = (s) -> s.length() > 0;

        predicate.test("foo");              // true
        predicate.negate().test("foo");     // false

        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();


        /**
         *
         *  Functions accept one argument and produce a result.
         *  Default methods can be used to chain multiple functions together (compose, andThen).
         *
         */

        // Functions
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");     // "123"


        /**
         * Suppliers produce a result of a given generic type.
         * Unlike Functions, Suppliers don't accept arguments.
         *
         */
        // Suppliers
        Supplier<PersonName> personSupplier = PersonName::new;
        personSupplier.get();   // new PersonName


        /**
         * Consumers represents operations to be performed on a single input argument.
         *
         */
        // Consumers
        Consumer<PersonName> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new PersonName("Luke", "Skywalker"));


        /**
         * Comparators are well known from older versions of Java.
         * Java 8 adds various default methods to the interface
         *
         */
        // Comparators

        // Comparator<PersonName> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName); // old implementation
        Comparator<PersonName> comparator = Comparator.comparing(p -> p.firstName);


        PersonName p1 = new PersonName("John", "Doe");
        PersonName p2 = new PersonName("Alice", "Wonderland");

        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);  // < 0


        /**
         * Optionals are not functional interfaces, instead it's a nifty utility to prevent NullPointerException.
         * It's an important concept for the next section, so let's have a quick look at how Optionals work.
         *
         * Optional is a simple container for a value which may be null or non-null.
         * Think of a method which may return a non-null result but sometimes return nothing.
         * Instead of returning null you return an Optional in Java 8.
         *
         */
        // Optionals
        Optional<String> optional = Optional.of("bam");

        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"

        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"


    }

    /**
     * A java.util.Stream represents a sequence of elements on which one or more operations can be performed.
     * Stream operations are either intermediate or terminal. While terminal operations return a result of a certain type,
     * intermediate operations return the stream itself so you can chain multiple method calls in a row.
     * Streams are created on a source, e.g. a java.util.Collection like lists or sets (maps are not supported).
     * Stream operations can either be executed sequential or parallel.
     * Use case: Pure functional programming
     */
    public void streamsSample() {
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        // call .stream() to convert any collection to stream (NOTE: Java 8 only)
        // print entries (stream) only starting with "a"
        System.out.println("\n\nWith 'a' only:");
        stringCollection
                .stream()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);

        System.out.println("\n\nSorted:");

        stringCollection
                .stream()
                .sorted()
                .filter((s) -> s.startsWith("a"))
                .forEach(System.out::println);


        // Use "map" to perform certain operation on each of the collection item
        // apply String::toUpperCase operation to each item (stream) in the collection
        System.out.println("\n\nUpperCase 'Map':");
        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);


        // Various matching operations can be used to check whether a certain predicate matches the stream.
        // All of those operations are terminal and return a boolean result.

        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true


        // Count is a terminal operation returning the number of elements in the stream as a long.
        long startsWithB =
                stringCollection
                        .stream()
                        .filter((s) -> s.startsWith("b"))
                        .count();

        System.out.println(startsWithB);    // 3

        // Reduce terminal operation performs a reduction on the elements of the stream with the given function.
        // The result is an Optional holding the reduced value.

        Optional<String> reduced =
                stringCollection
                        .stream()
                        .sorted()
                        .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);


        // anymatch() : iterates until condition is met
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });

        // Chaining behaviour
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s); // executes everytime
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);  // executes everytime
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s)); // only executes on filtered objects

        // How order matters

        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s); // executes for each object
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s); // executes for filtered values only
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));  // executes for filtered values only , upper case applied.

        // calling sort after filter  to reduce iterations
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .sorted((s1, s2) -> {
                    System.out.printf("sort: %s; %s\n", s1, s2);
                    return s1.compareTo(s2);
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));


        // streams cannot be reused once terminal operator is called
        // to reuse stream we need to create one again
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok, no error, this is terminal operation
        // ok, if no supplier, it will through exception that accessing stream after terminal operation
        streamSupplier.get().noneMatch(s -> true);  // similarly we have allMatch


        // Sometimes it's useful to transform a regular object stream to a primitive stream or vice versa.
        // For that purpose object streams support the special mapping operations mapToInt(), mapToLong() and mapToDouble:

        Stream.of("a1", "a2", "a3")
                .map(s -> s.substring(1))
                .mapToInt(Integer::parseInt)
                .max()
                .ifPresent(System.out::println);  // 3

        // Primitive streams can be transformed to object streams via mapToObj():

        IntStream.range(1, 4)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);



    }


    public  void streamsInDepth()
    {

        List<Person> persons =
                Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));

        // COLLECTORs
        // transform stream to another type
        List<Person> filtered =
                persons
                        .stream()
                        .filter(p -> p.name.startsWith("P"))
                        .collect(Collectors.toList());

        System.out.println(filtered);    // [Peter, Pamela]


        // generate a map based on grouping function
        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        personsByAge
                .forEach((age, p) -> System.out.format("age %s: %s\n", age, p));

        // if this would be primitive stream, we would be able to call stream().average()
        // since it is non primitive stream, we specify collector function and object it will apply on
        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));

        System.out.println(averageAge);     // 19.0



        // to show all statistics
        IntSummaryStatistics ageSummary =
                persons
                        .stream()
                        .collect(Collectors.summarizingInt(p -> p.age));

        System.out.println(ageSummary);
    }

    /**
     * As mentioned above streams can be either sequential or parallel.
     * Operations on sequential streams are performed on a single thread while operations on parallel streams are performed concurrent on multiple threads.
     * The following example demonstrates how easy it is to increase the performance by using parallel streams.
     * Use case: Fast streams processing
     */
    public void parallelStreamsSample() {
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // simple sort (Sequential)

        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));


        // Parallel sort

        t0 = System.nanoTime();

        count = values.parallelStream().sorted().count();
        System.out.println(count);

        t1 = System.nanoTime();

        millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));


    }

    // Use case: Avoid boiler plate map operations code
    public void mapNewOperations() {

        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach((id, val) -> System.out.println(val));

        map.computeIfPresent(3, (num, val) -> val + num); // lambda (num, val) get (key, value) from map
        map.get(3);             // val33

        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false

        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true

        map.computeIfAbsent(3, num -> "bam");
        map.get(3);             // val33

        map.remove(3, "val3");
        map.get(3);             // val33

        map.remove(3, "val33");
        map.get(3);             // null

        map.getOrDefault(42, "not found");  // not found

        //  Merging entries of a map is quite easy:

        map.merge(9, "val9", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9

        map.merge(9, "concat", (value, newValue) -> value.concat(newValue));
        map.get(9);             // val9concat

    }

}
