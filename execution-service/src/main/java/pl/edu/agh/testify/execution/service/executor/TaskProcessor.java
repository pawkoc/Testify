package pl.edu.agh.testify.execution.service.executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

class TaskProcessor {

    private static final Logger logger = LoggerFactory.getLogger(TaskProcessor.class);

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
        taskResult.grade();
        return taskResult;
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
