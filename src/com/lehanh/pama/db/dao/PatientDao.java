package com.lehanh.pama.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.lehanh.pama.db.DatabaseManager;
import com.lehanh.pama.patientcase.Patient;

public class PatientDao implements IDao {

	private static final String PATIENT_TABLE = "patient";
//  private static final String `id` SMALLINT NOT NULL AUTO_INCREMENT,
//	private static final String IMAGE_NAME = `image_name` VARCHAR(70),
//	private static final String `name` VARCHAR(70) NOT NULL,
//	private static final String `address` VARCHAR(150),
//	private static final String `birth_day` DATE NOT NULL,
//	private static final String `is_fermale` tinyint(1) NOT NULL,
//	private static final String `cell_phone` VARCHAR(12),
//	private static final String `phone` VARCHAR(15),
//	private static final String `email` VARCHAR(70),
//	private static final String `career` VARCHAR(70),	
//	private static final String `last_visit` DATE,
//	private static final String `last_surgery` DATE,
//	private static final String `next_appointment` DATE,
//	private static final String `patient_level` tinyint,
//	private static final String `note` VARCHAR(700),
//	private static final String `medical_personal_info` VARCHAR(5000),
	
	public void insert(Patient patient) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DatabaseManager.getInstance().getConn();
			ps = conn.prepareStatement(
					"insert into " + PATIENT_TABLE + 
					" (image_name, name, address, birth_day, is_fermale, cell_phone, phone, email, career, last_visit, last_surgery, " +
						"next_appointment, patient_level, note, medical_personal_info) " + 
					" values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
						, Statement.RETURN_GENERATED_KEYS);
			int i = 1;
			ps.setString(i++, patient.getImageName());
			ps.setString(i++, patient.getName());
			ps.setString(i++, patient.getAddress());
			ps.setDate(i++, SqlUtils.newSqlDate(patient.getBirthDay()));
			ps.setBoolean(i++, patient.isFermale());
			ps.setString(i++, patient.getCellPhone());
			ps.setString(i++, patient.getPhone());
			ps.setString(i++, patient.getEmail());
			ps.setString(i++, patient.getCareer());
			ps.setDate(i++, SqlUtils.newSqlDate(patient.getLastVisit()));
			ps.setDate(i++, SqlUtils.newSqlDate(patient.getLastSurgery()));
			ps.setDate(i++, SqlUtils.newSqlDate(patient.getNextAppointment()));
			ps.setInt(i++, patient.getPatientLevel());
			ps.setString(i++, patient.getNote());
			ps.setString(i++, patient.getMedicalPersonalInfoText());

			System.out.println("SQL: " + ps);
			int resultUpdate = ps.executeUpdate();
			if (resultUpdate <= 0) {
				return;
			}
			ResultSet resultIdKey = ps.getGeneratedKeys();
			if (resultIdKey.next()) {
				Long resultId = resultIdKey.getLong(1);
				patient.setId(resultId);
				return;
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
	
	public void update(Patient patient) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DatabaseManager.getInstance().getConn();
			ps = conn.prepareStatement(
					"update " + PATIENT_TABLE + 
					" set " +
					" image_name=?, name=?, address=?, birth_day=?, is_fermale=?, cell_phone=?, phone=?, email=?, career=?, " +
						" last_visit=?, last_surgery=?, next_appointment=?, " +
						" patient_level=?, note=?, medical_personal_info=? " + 
					" where id=? ");

			int i = 1;
			ps.setString(i++, patient.getImageName());
			ps.setString(i++, patient.getName());
			ps.setString(i++, patient.getAddress());
			ps.setDate(i++, SqlUtils.newSqlDate(patient.getBirthDay()));
			ps.setBoolean(i++, patient.isFermale());
			ps.setString(i++, patient.getCellPhone());
			ps.setString(i++, patient.getPhone());
			ps.setString(i++, patient.getEmail());
			ps.setString(i++, patient.getCareer());
			ps.setDate(i++, SqlUtils.newSqlDate(patient.getLastVisit()));
			ps.setDate(i++, SqlUtils.newSqlDate(patient.getLastSurgery()));
			ps.setDate(i++, SqlUtils.newSqlDate(patient.getNextAppointment()));
			ps.setInt(i++, patient.getPatientLevel());
			ps.setString(i++, patient.getNote());
			ps.setString(i++, patient.getMedicalPersonalInfoText());

			ps.setLong(i++, patient.getId());
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