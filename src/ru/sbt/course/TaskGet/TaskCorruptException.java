package ru.sbt.course.TaskGet;

public class TaskCorruptException extends RuntimeException {
    public TaskCorruptException(Throwable cause){
        super("TaskCorrupt: {" + cause.getMessage() + "}");
    }
}
