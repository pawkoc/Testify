package pl.edu.agh.testify.execution.service.executor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimpleCountingTestsResult implements TestsResult, Serializable {

    private final List<Test> passedTests = new ArrayList<>();
    private final List<Test> failedTests = new ArrayList<>();
    private Grade grade;

    @Override
    public void sucess(Test test) {
        passedTests.add(test);
    }

    @Override
    public void fail(Test test) {
        failedTests.add(test);
    }

    @Override
    public List<Test> getFailed() {
        return Collections.unmodifiableList(failedTests);
    }

    @Override
    public Grade grade() {
        double ratio = (double) passedTests.size() / (passedTests.size() + failedTests.size());
        double percent = ratio * 100;
        Grade grade = getGradeFromPercent(percent);
        this.grade = grade;
        return grade;
    }

    private Grade getGradeFromPercent(double percent) {
        if (Double.compare(percent, 50.) < 0) return Grade._2_0;
        else if (Double.compare(percent, 60.) < 0) return Grade._3_0;
        else if (Double.compare(percent, 70.) < 0) return Grade._3_5;
        else if (Double.compare(percent, 80.) < 0) return Grade._4_0;
        else if (Double.compare(percent, 90.) < 0) return Grade._4_5;
        else return Grade._5_0;
    }

    public List<Test> getPassedTests() {
        return passedTests;
    }

    public List<Test> getFailedTests() {
        return failedTests;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "SimpleCountingTestsResult{" +
                "passedTests=" + passedTests +
                ", failedTests=" + failedTests +
                ", grade=" + grade +
                '}';
    }
}
