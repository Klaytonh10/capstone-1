package org.example;

public class Person implements Comparable<Person>{

    private String name = "";
    private int age = 0;

    @Override
    public String toString() {
        String result = "Name: " + this.name + " Age: " + this.age;
        return result;
    }

    @Override
    public int compareTo(Person otherPerson) {
        // This is used to sort things simply
        return this.name.compareTo(otherPerson.name);
    }
}
