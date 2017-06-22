package pl.edu.agh.testify.results.service.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.testify.rabbitmq.message.TestResultMessage;
import pl.edu.agh.testify.results.service.ResultsService;

@Component
public class ResultsMessageReceiver {

    private final Logger logger = LoggerFactory.getLogger(ResultsMessageReceiver.class);

    private final ResultsService resultsService;

    @Autowired
    public ResultsMessageReceiver(ResultsService resultsService) {
        this.resultsService = resultsService;
    }

    @SuppressWarnings("unused")
    public void handleMessage(TestResultMessage message) {
        logger.info("Received <" + message + ">");
        resultsService.saveResults(message);
    }
}
