package com.lehanh.pama.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.lehanh.pama.db.DatabaseManager;
import com.lehanh.pama.patientcase.AppointmentSchedule;

public class ScheduleDao implements IDao {

	/*
    `id` SMALLINT NOT NULL AUTO_INCREMENT,
    `patient_id` SMALLINT NOT NULL,
	`appointment_date` DATE NOT NULL,
	`resolved` tinyint(1) NOT NULL,
	`appointment_type` SMALLINT,
    `note` VARCHAR(500),
	 */
	
	private static final String TABLE = "appointment";
	private static final String ID_COL = "id";
	private static final String PATIENT_ID_COL = "patient_id";
	private static final String DATE_COL = "appointment_date";
	private static final String APPOINTMENT_TYPE = "appointment_type";
	private static final String RESOLVED = "resolved";
	private static final String NOTE = "note";


	public List<AppointmentSchedule> loadAllAppointment(Date date) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseManager.getInstance().getConn();
			ps = conn.prepareStatement("select * from " + TABLE + " order by " + DATE_COL + " DESC");
			rs = ps.executeQuery();
			
			List<AppointmentSchedule> result = new LinkedList<AppointmentSchedule>();
			AppointmentSchedule aP;
			while (rs.next()) {
				aP = populate(rs);
				result.add(aP);
			}
			return result;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}

	private AppointmentSchedule populate(ResultSet rs) throws SQLException {
		return new AppointmentSchedule(rs.getLong(ID_COL), rs.getLong(PATIENT_ID_COL),
				rs.getDate(DATE_COL), rs.getLong(APPOINTMENT_TYPE), rs.getString(NOTE), rs.getBoolean(RESOLVED));
	}

	public Long insert(AppointmentSchedule item) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DatabaseManager.getInstance().getConn();
			ps = conn.prepareStatement(
					"insert into " + TABLE + 
						" (patient_id, appointment_date, appointment_type, resolved, note) " + 
						" values (?, ?, ?, ?, ? ) ", Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			ps.setLong(i++, item.getPatientId());
			ps.setDate(i++, SqlUtils.newSqlDate(item.getAppointmentDate()));
			ps.setLong(i++, item.getAppointmentType());
			ps.setBoolean(i++, item.isResolved());
			ps.setString(i++, item.getNote());
			System.out.println("SQL: " + ps);
			int resultUpdate = ps.executeUpdate();
			if (resultUpdate <= 0) {
				return 0l;
			}
			ResultSet resultIdKey = ps.getGeneratedKeys();
			if (resultIdKey.next()) {
				Long resultId = resultIdKey.getLong(1);
				item.setId(resultId);
				return resultId;
			}
			throw new SQLException("Insert statement did not generate an AutoID"); //$NON-NLS-1$
			
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
	
	public void update(AppointmentSchedule item) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DatabaseManager.getInstance().getConn();
			ps = conn.prepareStatement(
					"update " + TABLE + 
					" set patient_id=?, appointment_date=?, appointment_type=?, resolved=?, note=? " +
					" where id=? ");

			int i = 1;
			ps.setLong(i++, item.getPatientId());
			ps.setDate(i++, SqlUtils.newSqlDate(item.getAppointmentDate()));
			ps.setLong(i++, item.getAppointmentType());
			ps.setBoolean(i++, item.isResolved());
			ps.setString(i++, item.getNote());

			ps.setLong(i++, item.getId());
			System.out.println("SQL: " + ps);
			ps.executeUpdate();
		} finally {
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		}
	}
}