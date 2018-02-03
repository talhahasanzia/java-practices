package com.playground.java8.unsorted;

public class StringsDemo {

    public static void stringsTest() {
        // Strings are immutable - everytime it is manipulated it is allocated a new reference
        // and old one is allowed to be GCed

        String name = "First Name";   // new string
        name = name + " LastName";  // new string (again)


        // above allocation is equivalent to
        String name2 = "Fname";   // new string
        String name3 = name2 + " Lname";  // new string  , its immutable, everytime it creates new object in memory


        // Now to avoid this use StringBuilder
        StringBuilder stringBuilder = new StringBuilder("Fname"); // new string object as StringBbuilder
        // if another thread accesses this object it will have full authority to change its state
        // if this happens, append operations will result in inconsistent data stored in this object
        stringBuilder.append(" Lname"); // appended in that string , did not created new object. ie - Mutable


        // There is also extension of StringBuilder as StringBuffer.
        // StringBuffer is thread-safe. But it has impact on performance.

        StringBuffer stringBuffer=new StringBuffer("Some data");  // new object
        // its synchronized
        // any operation called during the append will have to wait for this operation to be completed
        // this will help sustaining data state where it can be accessed from multiple threads

        stringBuffer.append(" More data"); // same object used, mutable


    }

}
