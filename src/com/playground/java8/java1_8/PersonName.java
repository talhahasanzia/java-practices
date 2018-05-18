package com.playground.java8.java1_8;

public class PersonName {
        String firstName;
        String lastName;

        PersonName() {
        }

        PersonName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public String toString() {
            return firstName + "," + lastName;
        }

    }