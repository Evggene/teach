package org.example.lambda;

public class Sum implements Calculation{
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
}
