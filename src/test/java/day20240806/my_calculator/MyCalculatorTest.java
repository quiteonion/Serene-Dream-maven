package day20240806.my_calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyCalculatorTest {

    @Test
    void add() {
        int number = 5;
        int add = MyCalculator.add(2,3);
        assertEquals(number, add);
    }
}