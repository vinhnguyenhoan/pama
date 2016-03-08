package com.lehanh.pama.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.lehanh.pama.db.DatabaseManager;
import com.lehanh.pama.patientcase.AppointmentSchedule;

public class ScheduleDao implements IDao {

	private static final String TABLE = "appointment";
	private static final String ID_COL = "c_id";
	private static final String PATIENT_ID_COL = "patient_id";
	private static final String DATE_COL = "appointment_date";
	private static final String APPOINTMENT_TYPE = "appointment_type";

	public List<AppointmentSchedule> loadAllAppointment(Date date) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DatabaseManager.getInstance().getConn();
			ps = conn.prepareStatement("select * from " + TABLE + " orderBy " + DATE_COL + " DESC");
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
		return new AppointmentSchedule(rs.getLong(ID_COL),
				rs.getLong(PATIENT_ID_COL),
				rs.getDate(DATE_COL),
				rs.getLong(APPOINTMENT_TYPE));
	}

}