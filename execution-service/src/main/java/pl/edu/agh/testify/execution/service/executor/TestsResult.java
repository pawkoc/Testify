package pl.edu.agh.testify.execution.service.executor;

public interface TestsResult {
    void sucess(Test test);
    void fail(Test test);
    Grade grade();
}
