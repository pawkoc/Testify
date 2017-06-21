package pl.edu.agh.testify.execution.service.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;

public class TaskProcessor implements Callable<TestsResult> {

    private static final Logger logger = LoggerFactory.getLogger(TaskProcessor.class);
    private final List<Test> tests;
    private final Path parentDir;
    private Path jar;

    public TaskProcessor(Path absoluteJarPath, List<Test> tests) {
        this.jar = absoluteJarPath.getFileName();
        this.parentDir = absoluteJarPath.getParent();
        this.tests = tests;
    }

    static TestsResult executeJar(Path absoluteJarPath, List<Test> tests) throws IOException {
        TestsResult taskResult = new SimpleCountingTestsResult();
        Path jar = absoluteJarPath.getFileName();
        Path parentDir = absoluteJarPath.getParent();
        for (Test test : tests) {
            String result = executeJar(test.input(), jar, parentDir);
            if (test.passes(result)) {
                taskResult.sucess(test);
            } else {
                taskResult.fail(test);
            }
        }
        return taskResult;
    }

    @Override
    public TestsResult call() throws Exception {
//        TestsResult taskResult = new SimpleCountingTestsResult();
//        for (Test test : tests) {
//            String result = executeJar(test.input());
//            if (test.passes(result)) {
//                taskResult.sucess(test);
//            } else {
//                taskResult.fail(test);
//            }
//        }
//        return taskResult;
        return null;
    }

    private static String executeJar(String input, Path jar, Path parentDir) throws IOException {
        logger.debug("Execuce jar with input: " + input);
        ProcessBuilder pb = new ProcessBuilder("java", "-jar", jar.toString());
        pb.directory(parentDir.toFile());
        Process process = pb.start();

        OutputStream stdin = process.getOutputStream();
        InputStream stdout = process.getInputStream();

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(stdin));
        Scanner scanner = new Scanner(stdout);

        writer.write(input);
        writer.flush();
        writer.close();

        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }
}
