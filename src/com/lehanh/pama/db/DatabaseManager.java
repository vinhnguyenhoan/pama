package com.lehanh.pama.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.lehanh.pama.util.PamaHome;

public class DatabaseManager {
	
	private static DatabaseManager instance;
	private Connection connection;
	
	private DatabaseManager() {
	}
	
	public static void initialize() throws SQLException, IOException {
		if (instance != null) {
			throw new RuntimeException("DatabaseManager is already initialized."); //$NON-NLS-1$
		}
		instance = new DatabaseManager();
		instance.initConnectionIfNeeded();
	}

	public static DatabaseManager getInstance() {
		return instance;
	}

	private void initConnectionIfNeeded() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			return;
		}
		try {
			PamaHome.application.logInfo("Loading db driver..."); //$NON-NLS-1$
			Class.forName("com.mysql.jdbc.Driver"); //$NON-NLS-1$
		} catch (ClassNotFoundException e) {
			SQLException se = new SQLException("Failed to load db driver"); //$NON-NLS-1$
			se.initCause(e);
			throw se;
		}
		openConnection();
	}
	
	private void openConnection() throws SQLException {
		PamaHome.application.logInfo("Opening db connection..."); //$NON-NLS-1$
		String dburl = null;
		if (PamaHome.getDbPass() == null || PamaHome.getDbName() == null) {
			dburl = "jdbc:mysql://localhost/" + PamaHome.getDbName() + "?user=root&password=root" + PamaHome.getDbPass();
		} else {
			dburl = "jdbc:mysql://" + PamaHome.getDbIp() + "/" + PamaHome.getDbName() + "?" +
					"user=" + PamaHome.getDbUserName() + "&password=" + PamaHome.getDbPass();
		}
		connection = DriverManager.getConnection(dburl + ";create=false");
		// TODO for later ? migrationCheck();
		// TODO prepareStatements();
	}

	public Connection getConn() {
		return this.connection;
	}
}
