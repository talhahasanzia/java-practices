package com.playground.java8.unsorted;

import java.util.LinkedHashMap;

public class Sample {

    public static void firstNonRepeating()
    {

       String s = "abacabad";

        char[] array=s.toCharArray();

        LinkedHashMap<Character, Integer> pairs=new LinkedHashMap<>();


        for(char c:array)
        {

            if(pairs.containsKey(new Character(c)))
            {

            }
        }

    }

}
