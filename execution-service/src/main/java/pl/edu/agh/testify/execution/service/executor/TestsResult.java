package pl.edu.agh.testify.execution.service.executor;

import java.util.List;

public interface TestsResult {
    void sucess(Test test);
    void fail(Test test);
    List<Test> getFailed();
    Grade grade();
}
