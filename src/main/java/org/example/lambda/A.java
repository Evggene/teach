package org.example.lambda;

public class A {
    public static void main(String[] args) {
        Calculation sum = (a, b) -> a + b;
        System.out.println(sum.calculate(3, 4));
    }
}
