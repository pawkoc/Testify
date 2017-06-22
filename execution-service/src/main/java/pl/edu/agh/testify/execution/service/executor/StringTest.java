package pl.edu.agh.testify.execution.service.executor;

public class StringTest implements Test {

    private final String input;
    private final String expectedOutput;

    public StringTest(String input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Override
    public boolean passes(String output) {
        return output.equals(expectedOutput);
    }

    @Override
    public String input() {
        return input;
    }
}
