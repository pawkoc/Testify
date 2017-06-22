package pl.edu.agh.testify.execution.service.executor;

public class StringTest implements Test {

    private final String input;
    private final String expectedOutput;
    private String output;

    public StringTest(String input, String expectedOutput) {
        this.input = input;
        this.expectedOutput = expectedOutput;
    }

    @Override
    public boolean passes(String output) {
        this.output = output;
        return output.equals(expectedOutput);
    }

    @Override
    public String input() {
        return input;
    }

    @Override
    public String output() {
        return output;
    }

    @Override
    public String expectedOutput() {
        return expectedOutput;
    }

    @Override
    public String toString() {
        return "StringTest{" +
                "input='" + input + '\'' +
                ", expectedOutput='" + expectedOutput + '\'' +
                ", output='" + output + '\'' +
                '}';
    }
}
