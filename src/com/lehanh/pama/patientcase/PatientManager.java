package com.lehanh.pama.patientcase;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TreeMap;

import com.lehanh.pama.ICatagoryManager;
import com.lehanh.pama.IPatientManager;
import com.lehanh.pama.catagory.AppointmentCatagory;
import com.lehanh.pama.catagory.Catagory;
import com.lehanh.pama.catagory.CatagoryManager;
import com.lehanh.pama.catagory.CatagoryType;
import com.lehanh.pama.db.dao.ScheduleDao;
import com.lehanh.pama.util.PamaException;
import com.lehanh.pama.util.PamaHome;

public class PatientManager implements IPatientManager {

	private List<AppointmentSchedule> listAppointmentToday;
	
	private ICatagoryManager catagoryManager;
	
	@Override
	public void initialize() throws SQLException {
		catagoryManager = (ICatagoryManager) PamaHome.getService(CatagoryManager.class);
		// Load all appointment
		reloadAppointment();
	}

	private void reloadAppointment() throws SQLException {
		GregorianCalendar calToday = new GregorianCalendar();
		calToday.set(Calendar.HOUR_OF_DAY, 0);
		calToday.set(Calendar.MINUTE, 0);
		calToday.set(Calendar.SECOND, 0);
		listAppointmentToday = new ScheduleDao().loadAllAppointment(calToday.getTime());
		
		TreeMap<Long, Catagory> allAppointmentType = catagoryManager.getCatagoryByType(CatagoryType.APPOINTMENT);
		for (AppointmentSchedule aS : listAppointmentToday) {
			if (aS.getAppointmentType() == null || aS.getAppointmentType() <= 0) {
				continue;
			}
			Catagory appointmentType = allAppointmentType.get(aS.getAppointmentType());
			if (appointmentType == null) {
				throw new PamaException("Cannot find appointmentType " + aS.getAppointmentType());
			}
			aS.setAppointmentCatagory((AppointmentCatagory) appointmentType);
		}
	}

	@Override
	public Patient getPatientDetailById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> searchPatient(Date appointment, Date lastUpdate,
			String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Patient> searchPatientAppointmentToday(boolean forceReload) {
		if (listAppointmentToday == null) {
			try {
				reloadAppointment();
			} catch (SQLException e) {
				throw new PamaException("Cannot reload appointment", e);
			}
		}
		// TODO
		return null;
	}

	@Override
	public int clearOldAppointment() {
		// TODO Auto-generated method stub
		return 0;
	}

}
