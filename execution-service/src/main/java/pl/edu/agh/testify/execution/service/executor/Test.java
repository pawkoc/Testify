package pl.edu.agh.testify.execution.service.executor;

public interface Test {
    boolean passes(String output);
    String output();
    String expectedOutput();
    String input();
}
