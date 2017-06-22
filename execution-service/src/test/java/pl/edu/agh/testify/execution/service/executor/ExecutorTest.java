package pl.edu.agh.testify.execution.service.executor;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class ExecutorTest {

    private TaskExecutor executor;
    private static TestProvider testProvider;

    @BeforeClass
    public static void setUpClass() throws Exception {
        testProvider = Mockito.mock(TestProvider.class);
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void executorImplementationExists() throws Exception {
        assertThat(executor).isNotNull();
    }
}
