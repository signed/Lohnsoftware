package example.lohnsoftware.http.explore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;

public class DefaultAsyncLogik implements AsyncLogik {
    private static final Logger logger = LoggerFactory.getLogger(DefaultAsyncLogik.class);
    @Override
    @Async
    public void execute() {
        MDC.put("", "");
        logger.info("AsyncLogik executing...");
    }
}
