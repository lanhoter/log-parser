package test.com.mengyu.controller;

import com.mengyu.model.ServerLog;
import com.mengyu.model.Event;
import com.mengyu.controller.EventController;
import org.junit.Test;
import static org.junit.Assert.*;

public class EventControllerTest {
    private static final String TYPE = "testType";
    private static final String ID = "test123";
    private static final String HOST_NAME = "testHost";
    private static final Long START_TIME_STAMP =  Long.valueOf(123);
    private EventController eventController = new EventController();
    ServerLog startEvent = new ServerLog(ID, ServerLog.State.STARTED, TYPE, HOST_NAME, START_TIME_STAMP);

    @Test
    public void test_GenerateEventWithValidServerLog() {
        ServerLog finishEvent = new ServerLog(ID, ServerLog.State.FINISHED, TYPE, HOST_NAME, Long.valueOf(125));
        Event event = eventController.generateEvent(startEvent, finishEvent);
        assertFalse("Should return isAlert() = FALSE", event.isAlert());
    }

    @Test
    public void test_GenerateEventWithInvalidServerLog() {
        ServerLog finishEvent = new ServerLog(ID, ServerLog.State.FINISHED, TYPE, HOST_NAME, Long.valueOf(150));
        Event event = eventController.generateEvent(startEvent, finishEvent);
        assertTrue("Should return isAlert() = TRUE", event.isAlert());
    }
}