package ru.sbt.course.TaskGet;
/**
 * SummCounterWithExc - класс, вычисляющий сумму всех чисел от 1 до value.
 * Если сумма делится нацело на 10, выкидывает исключение.
 */

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class SummCounterWithExc implements Callable<String> {
    private int value;

    public SummCounterWithExc(int val) {
        value = val;
    }

    @Override
    public String call() throws Exception {

        int result = 0;
        for (int i = 1; i <= value; i++) {
            result += i;
        }
        // тянем время имитируя длительные вычисления
        long delay = System.currentTimeMillis() + 100;
        while (delay > System.currentTimeMillis()) {
            TimeUnit.MILLISECONDS.sleep(1);
        }


        if (result % 10 == 0)
            throw new IllegalArgumentException("Результат делится на 10 (summ" + value + "=" + result + ")! из " +
                    Thread.currentThread().getName());

        return "Summ " + value + " = " + result + " из " + Thread.currentThread().getName();
    }
}
