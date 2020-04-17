package ru.sbt.course.TaskGet;
/**
 * Task - класс, выполняющий задачу
 * Домашнее задание по уроку 13 СБТ (Java memory model)
 *
 * @author - Hin
 * @version - 1.0 16/04/2020
 */

import java.util.concurrent.Callable;

public class Task<T> {

    private volatile boolean isReady = false;
    private volatile boolean isExcept = false;

    private T result;
    private TaskCorruptException exception;
    private Callable<?> callable;

    public Task(Callable<? extends T> callable) {
        this.callable = callable;
    }

    public  T get() throws TaskCorruptException {

        if (isReady)
            return result;


        synchronized (this) {
            if (isReady)
                return result;
            if (isExcept)
                throw exception;

            try {
                result = (T) callable.call();
                isReady = true;
                return result;
            } catch (Exception e) {
                exception = new TaskCorruptException(e);
                isExcept = true;
                throw exception;
            }
        }
    }
}
