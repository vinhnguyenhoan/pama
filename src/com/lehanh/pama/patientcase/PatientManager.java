package com.lehanh.pama.patientcase;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;

import org.eclipse.swt.widgets.Display;

import com.lehanh.pama.ICatagoryManager;
import com.lehanh.pama.IPatientManager;
import com.lehanh.pama.catagory.AppointmentCatagory;
import com.lehanh.pama.catagory.Catagory;
import com.lehanh.pama.catagory.CatagoryType;
import com.lehanh.pama.db.dao.PatientDao;
import com.lehanh.pama.db.dao.ScheduleDao;
import com.lehanh.pama.util.PamaException;
import com.lehanh.pama.util.PamaHome;

public class PatientManager implements IPatientManager {

	private List<AppointmentSchedule> listAppointmentToday;
	
	private ICatagoryManager catagoryManager;

	private Patient patientSelected;
	
	private List<IPatientViewPartListener> paListeners = new LinkedList<IPatientViewPartListener>();

	private class NotifyPaRunnable implements Runnable {
		
		private Patient oldPa;
		private Patient newPa;
		
		@Override
		public void run() {
			for (IPatientViewPartListener pL : paListeners) {
				pL.patientChanged(oldPa, newPa);
			}
		}
	}
	
	private final NotifyPaRunnable notifyPaRunnable = new NotifyPaRunnable();
	
	@Override
	public void initialize() throws SQLException {
		catagoryManager = (ICatagoryManager) PamaHome.getService(ICatagoryManager.class);
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
	public void updatePatient(String imagePath, String name, String address, Date birthDay, boolean isFermale,
			String cellPhone, String phone, String email, String career, int patientLevel, String note,  String detailExam,
			String medicalHistory, String anamnesis) {
		Patient result = new Patient();
		if (patientSelected != null) {
			result.setId(patientSelected.getId());
		}
		result.setName(name);
		result.setAddress(address);
		result.setBirthDay(birthDay);
		result.setFermale(isFermale);
		result.setCellPhone(cellPhone);
		result.setPhone(phone);
		result.setEmail(email);
		result.setCareer(career);
		result.setPatientLevel(patientLevel);
		result.setNote(note);
		
		MedicalPersonalInfo medicalPersonalInfo = null;
		if (patientSelected != null) {
			medicalPersonalInfo = patientSelected.getMedicalPersonalInfo();
		}
		if (medicalPersonalInfo == null) {
			medicalPersonalInfo = new MedicalPersonalInfo();
		}
		medicalPersonalInfo.setAnamnesis(anamnesis);
		medicalPersonalInfo.setMedicalHistory(medicalHistory);
		medicalPersonalInfo.setPatientCaseSummary(detailExam);
		result.setMedicalPersonalInfo(medicalPersonalInfo);
		
		try {
			if (result.getId() == null) {
				new PatientDao().insert(result);
			} else {
				new PatientDao().update(result);
			}
		} catch (SQLException e) {
			throw new PamaException("Lổi cập nhật DB: " + e.getMessage());
		}
		notifyPaListener(patientSelected, result, paListeners);
		this.patientSelected = result;
	}
	
	private void notifyPaListener(final Patient oldPa, final Patient newPa, final List<IPatientViewPartListener> paListeners) {
		notifyPaRunnable.oldPa = oldPa;
		notifyPaRunnable.newPa = newPa;
		Display.getCurrent().asyncExec(notifyPaRunnable );
	}

	@Override
	public synchronized void addPaListener(IPatientViewPartListener paL) {
		this.paListeners.add(paL);
	}
	
	@Override
	public Patient updateImage(String image) {
		
		return null;
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

	@Override
	public Patient getCurrentPatient() {
		return this.patientSelected;
	}

}
