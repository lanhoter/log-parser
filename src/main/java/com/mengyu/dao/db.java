package com.mengyu.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import com.mengyu.model.ConfigBeanValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

public class db {

	@Autowired
	private static ConfigBeanValue configBeanValue;

	private static final String dbUrl = "jdbc:hsqldb:file:eventdb;ifexists=false";
	private static final String dbPropertyName = "user";
	private static final String dbPropertyPassword = "";
	private static final String dbCreateTableStmt = "CREATE TABLE IF NOT EXISTS event (id VARCHAR(20), duration INTEGER, type VARCHAR(50), host VARCHAR(50), alert BOOLEAN)";

	private static Logger logger = LoggerFactory.getLogger(db.class);

    public static Connection getConnection() throws SQLException {
		try {
			// load and register the HSQL Database Engine driver
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			Connection conn = DriverManager.getConnection(dbUrl, dbPropertyName, dbPropertyPassword);
			conn.createStatement().execute(dbCreateTableStmt);
			return conn;
		} catch (Exception e) {
			logger.error("Failed to initialising HSQL JDBCDriver");
			throw new Error("Failed to create a Connection", e);
		}
    }
}