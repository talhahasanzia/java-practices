package com.playground.streamapidemo;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.TextView;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Stream API is not supported until API level 24.
 * <p>
 * Other features of JAva 8 works on all platforms.
 *
 *
 * A java.util.Stream represents a sequence of elements on which one or more operations can be performed.
 * Stream operations are either intermediate or terminal.
 * While terminal operations return a result of a certain type,
 * intermediate operations return the stream itself so you can chain multiple method calls in a row.
 * Streams are created on a source, e.g. a java.util.Collection like lists or sets (maps are not supported).
 * Stream operations can either be executed sequential or parallel.
 */
public class MainActivity extends AppCompatActivity implements InstanceChecker<MainActivity> {

    TextView sampleText;
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sampleText = findViewById(R.id.sample_textview);

        sampleText.setMovementMethod(new ScrollingMovementMethod());

        Log.d("Main", "onCreate: " + instanceOf(this));  // default methods in interface


        lambdaSample();


        funcInterfaceSample();

        methodReferencesSample();

        builtInFunctionalInterfacesSample();


    }

    @Override
    protected void onResume() {
        super.onResume();

        // Serialization example

/*
        DataModel dataModel = new DataModel(5575355089l, "Some Data");

        Intent intent = new Intent(MainActivity.this, SampleActivity.class);

        intent.putExtra("data", dataModel);

        startActivity(intent);*/
    }

    /**  Java 8 streams **/





    /**
     * Preliminaries : Java 8 specific topics
     **/

    String resultString = "Unsorted:\n";


    void lambdaSample() {

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");


        names.stream().forEach((String name) -> resultString += name + "\n");  // lambda + stream


        Collections.sort(names);


        resultString += "\n\nSorted:\n\n";

        names.stream().forEach((String name) -> resultString += name + "\n");  // lambda + stream

        sampleText.setText(resultString);

    }


    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }


    @FunctionalInterface
    interface Prefixer<F, T> {
        T addPrefix(F name, int FLAG);

        int MALE = 0;
        int FEMALE = 1;
    }


    void funcInterfaceSample() {

        Converter<String, Integer> converterNumber = (from) -> Integer.valueOf(from);

        Integer converted = converterNumber.convert("123");


        Log.d(TAG, "funcInterfaceSample: String, Integer >> " + converted);    // 123


        Prefixer<String, String> prefixer = (name, FLAG) -> FLAG == Prefixer.MALE ? "Mr." + name : "Ms." + name;


        Log.d(TAG, "funcInterfaceSample: Prefixer: " + prefixer.addPrefix("Michael", Prefixer.MALE));



        Log.d(TAG, "funcInterfaceSample: Prefixer: " + prefixer.addPrefix("Sarah", Prefixer.FEMALE));

    }


    void methodReferencesSample()
    {
        // using :: to pass method or constructor as references

        // referenced method's parameter and return types should match specified generic types
        Converter<String, Integer> converter = Integer::valueOf; // Integer's valueOf() has INteger return type & String as parameter.
        Integer converted = converter.convert("355624");

        Log.d(TAG, "methodReferencesSample: "+converted);


        // instance method references
        Something something = new Something();
        Converter<String, String> instanceRef = something::startsWith;
        String instanceResult = instanceRef.convert("Java");
        Log.d(TAG, "methodReferencesSample: Instance method res: "+instanceResult);

        PersonFactory<Person> personFactory = Person::new;  // passing constructor references
        Person person = personFactory.create("Peter", "Parker");
        Log.d(TAG, "methodReferencesSample: Person object - "+person.toString());

    }


    interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }


    private class Person {
        String firstName;
        String lastName;

        Person() {}

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return firstName+","+lastName;
        }

    }


    private class Something {
        String startsWith(String s) {
            return String.valueOf(s.charAt(0));
        }



    }


    void builtInFunctionalInterfacesSample()
    {

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
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();   // new Person


        /**
         * Consumers represents operations to be performed on a single input argument.
         *
         */
        // Consumers
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.firstName);
        greeter.accept(new Person("Luke", "Skywalker"));



        /**
         * Comparators are well known from older versions of Java.
         * Java 8 adds various default methods to the interface
         *
         */
        // Comparators

       // Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName); // old implementation
        Comparator<Person> comparator = Comparator.comparing(p -> p.firstName);


        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");

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



}
