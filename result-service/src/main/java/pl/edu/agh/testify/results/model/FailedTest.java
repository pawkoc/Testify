package pl.edu.agh.testify.results.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class FailedTest {

    @Id @GeneratedValue
    private long id;

    @ManyToOne
    private Result result;

    private String expectedOutput;
    private String actualOutput;

    public FailedTest() {}

    public FailedTest(Result result, String expectedOutput, String actualOutput) {
        this.result = result;
        this.expectedOutput = expectedOutput;
        this.actualOutput = actualOutput;
    }

    public FailedTest(String expectedOutput, String actualOutput) {
        this.expectedOutput = expectedOutput;
        this.actualOutput = actualOutput;
    }

    public long getId() {
        return id;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getExpectedOutput() {
        return expectedOutput;
    }

    public void setExpectedOutput(String expectedOutput) {
        this.expectedOutput = expectedOutput;
    }

    public String getActualOutput() {
        return actualOutput;
    }

    public void setActualOutput(String actualOutput) {
        this.actualOutput = actualOutput;
    }
}
