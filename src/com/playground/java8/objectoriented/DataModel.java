package com.playground.java8.objectoriented;


// Encapsulation: Information hiding, private members exposed public only when needed
public class DataModel {

    private String name;
    private int age;
    private String city;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws Exception {
        // Encapsulation also controls how data is handled
        if (age < 1)
            throw new Exception("Age not valid");

        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }




}
