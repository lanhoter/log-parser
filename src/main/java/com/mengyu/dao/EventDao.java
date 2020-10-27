package com.mengyu.dao;

import com.mengyu.model.Event;
import com.mengyu.dao.db;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.Connection;

@Component
public class EventDao {
    private static Logger logger = LoggerFactory.getLogger(EventDao.class);

    private static String sql = "INSERT INTO event (Id, Duration, Type, Host, Alert)  VALUES (?, ?, ?, ?, ?)";

    public static Boolean prepareAndInsertSqlQuery(Event event) {
        try(
            Connection conn = db.getConnection();
            PreparedStatement preparedStmt = conn.prepareStatement(sql)
        ){
            preparedStmt.setString(1, isNullOrEmpty(event.getId()));
            preparedStmt.setLong(2, event.getDuration());
            preparedStmt.setString(3, isNullOrEmpty(event.getType()));
            preparedStmt.setString(4, isNullOrEmpty(event.getHost()));
            preparedStmt.setBoolean(5, event.isAlert());
            logger.info(preparedStmt.toString());
            return preparedStmt.executeUpdate() > 0;
        } catch (Exception e) {
            logger.error("Failed to insert event into databse",  e);
            return false;
        }
    }

    public static String isNullOrEmpty(String str) {
        if(str != null && !str.isEmpty())
            return str;
        return "";
    }
}