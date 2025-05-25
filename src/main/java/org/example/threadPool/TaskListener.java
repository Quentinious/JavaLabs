package org.example.threadPool;

public interface TaskListener {
    void taskStarted(Task task);
    void taskFinished(Task task);
    void taskInterrupted(Task task);
}
