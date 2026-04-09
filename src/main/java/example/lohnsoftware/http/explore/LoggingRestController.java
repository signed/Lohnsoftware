package example.lohnsoftware.http.explore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoggingRestController {
    private static final Logger logger = LoggerFactory.getLogger(LoggingRestController.class);

    private final AsyncLogik asyncLogik;

    public LoggingRestController(AsyncLogik asyncLogik) {
        this.asyncLogik = asyncLogik;
    }

    @GetMapping(value = "/explore/logging/mdcForwardToAsyncTasks")
    public void mdcForwardToAsyncTasks(){
        logger.info("""
                {
                  "step": "entered"
                }
                """);
        MDC.put("controller", "mdc value");
        asyncLogik.execute();
        logger.info("""
                {
                  "step": "executed"
                }
                """);
        MDC.remove("controller");
    }
}
