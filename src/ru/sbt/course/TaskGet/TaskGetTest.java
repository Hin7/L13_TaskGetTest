package ru.sbt.course.TaskGet;

/**
 * Домашнее задание по уроку 13 СБТ (Java memory model)
 *
 * @author - Hin
 * @version - 1.0 16/04/2020
 */


public class TaskGetTest {
    public static void main(String[] args) {

        for (int i = 1; i < 16; i++) {

            Task<String> task = new Task<>(new SummCounterWithExc(i));

            for (int j = 0; j < 10; j++)
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            System.out.println(Thread.currentThread().getName() + ": " +
                                    task.get());
                        } catch (TaskCorruptException e) {
                            System.out.println(Thread.currentThread().getName() + ": " + e.getMessage());
                        }

                    }
                }.start();
        }
    }
}
