package com.mengyu.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mengyu.model.Event;
import com.mengyu.model.ServerLog;
import com.mengyu.dao.EventDao;
import com.mengyu.model.ServerLog;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

public class EventController {

    private static Logger logger = LoggerFactory.getLogger(EventController.class);
    private static final Gson gson = new Gson();
    private static Map<String, ServerLog> startedEventMap = new ConcurrentHashMap<>();
    private static Map<String, ServerLog> finishedEventMap = new ConcurrentHashMap<>();

    public static void parseLogFile(String filePath) {
        String thisLine = null;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while ((thisLine = bufferedReader.readLine()) != null) {
                ServerLog serverLog = gson.fromJson(thisLine, ServerLog.class);
                logger.info("parsed server log: {}", serverLog);
                if (serverLog.getState().equals(ServerLog.State.STARTED)) {
                    startedEventMap.put(serverLog.getId(), serverLog);
                } else {
                    finishedEventMap.put(serverLog.getId(), serverLog);
                }
            }

            for (String id : startedEventMap.keySet()) {
                ServerLog startEvent = startedEventMap.get(id);
                ServerLog finishEvent = finishedEventMap.get(id);
                if (startEvent!= null && finishEvent != null) {
                    logger.info("Generate event from ServerLog");
                    Event event = generateEvent(startEvent, finishEvent);
                    EventDao.prepareAndInsertSqlQuery(event);
                } else {
                    logger.error("id is missing for serverLog");
                }
            }
        } catch (IOException e) {
            logger.error("Failed to read the log file. ", e);
            e.printStackTrace();
        }
    }

    // public to be unit tested only
    public static Event generateEvent(ServerLog startEvent, ServerLog finishEvent) {
        Long duration = finishEvent.getTimestamp() - startEvent.getTimestamp();
        boolean isAlert = duration > 4;
        return new Event(startEvent.getId(), duration, startEvent.getType(), startEvent.getHost(), isAlert);
    }

}