package com.lehanh.pama.patientcase;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Display;

import com.lehanh.pama.ICatagoryManager;
import com.lehanh.pama.catagory.AppointmentCatagory;
import com.lehanh.pama.catagory.Catagory;
import com.lehanh.pama.catagory.CatagoryType;
import com.lehanh.pama.catagory.DiagnoseCatagory;
import com.lehanh.pama.catagory.DrCatagory;
import com.lehanh.pama.catagory.PrognosticCatagory;
import com.lehanh.pama.catagory.ServiceCatagory;
import com.lehanh.pama.catagory.SurgeryCatagory;
import com.lehanh.pama.db.dao.PatientDao;
import com.lehanh.pama.db.dao.ScheduleDao;
import com.lehanh.pama.util.PamaException;
import com.lehanh.pama.util.PamaHome;

public class PatientManager implements IPatientManager, IPatientSearcher, IAppointmentManager {

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
	
	/* (non-Javadoc)
	 * @see com.lehanh.pama.IService#initialize()
	 */
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
		
		Map<Long, Catagory> allAppointmentType = catagoryManager.getCatagoryByType(CatagoryType.APPOINTMENT);
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
	public IPatientSearcher getPatientSearcher() {
		return this;
	}

	/////////////////////////////// com.lehanh.pama.patientcase.IPatientSearcher /////////////////////////////// 
	/* (non-Javadoc)
	 * @see com.lehanh.pama.patientcase.IPatientSearcher#getPatientDetailById(java.lang.Long)
	 */
	@Override
	public Patient getPatientDetailById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.lehanh.pama.patientcase.IPatientSearcher#searchPatient(java.util.Date, java.util.Date, java.lang.String)
	 */
	@Override
	public List<Patient> searchPatient(Date appointment, Date lastUpdate,
			String name) {
		// TODO Auto-generated method stub
		return null;
	}

	/////////////////////////////// com.lehanh.pama.patientcase.IAppointmentManager /////////////////////////////// 
	@Override
	public IAppointmentManager getAppointmentManager() {
		return this;
	}
	
	/* (non-Javadoc)
	 * @see com.lehanh.pama.patientcase.IAppointmentManager#searchPatientAppointmentToday(boolean)
	 */
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

	/* (non-Javadoc)
	 * @see com.lehanh.pama.patientcase.IAppointmentManager#clearOldAppointment()
	 */
	@Override
	public int clearOldAppointment() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/////////////////////////////// com.lehanh.pama.patientcase.IPatientManager /////////////////////////////// 
	/* (non-Javadoc)
	 * @see com.lehanh.pama.patientcase.IPatientManager#updatePatient(java.lang.String, java.lang.String, java.lang.String, java.util.Date, boolean, java.lang.String, java.lang.String, java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
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
			medicalPersonalInfo.setAnamnesis(anamnesis);
			medicalPersonalInfo.setMedicalHistory(medicalHistory);
			medicalPersonalInfo.setPatientCaseSummary(detailExam);
		}
		
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
		this.patientSelected = result; // update patientSelected before call cancelEditing
		getCurrentPatient().reloadMedicalInfo();
	}
	
	private void notifyPaListener(final Patient oldPa, final Patient newPa, final List<IPatientViewPartListener> paListeners) {
		notifyPaRunnable.oldPa = oldPa;
		notifyPaRunnable.newPa = newPa;
		Display.getCurrent().asyncExec(notifyPaRunnable);
	}

	/* (non-Javadoc)
	 * @see com.lehanh.pama.patientcase.IPatientManager#addPaListener(com.lehanh.pama.patientcase.IPatientViewPartListener)
	 */
	@Override
	public synchronized void addPaListener(IPatientViewPartListener paL) {
		this.paListeners.add(paL);
	}
	
	/* (non-Javadoc)
	 * @see com.lehanh.pama.patientcase.IPatientManager#getCurrentPatient()
	 */
	@Override
	public Patient getCurrentPatient() {
		return this.patientSelected;
	}

	/* (non-Javadoc)
	 * @see com.lehanh.pama.patientcase.IPatientManager#updatePatientCase(com.lehanh.pama.patientcase.PatientCaseEntity, com.lehanh.pama.catagory.DrCatagory, java.util.List, java.util.List, java.lang.String, java.util.List, java.lang.String, java.lang.String, java.lang.String, java.util.List, java.lang.String, java.util.Date, boolean, boolean, java.lang.String, java.lang.String, java.util.Date, com.lehanh.pama.catagory.AppointmentCatagory, java.lang.String)
	 */
	@Override
	public void updatePatientCase(PatientCaseEntity paCaseEntity,
			DrCatagory drCat, List<ServiceCatagory> serviceList,
			List<PrognosticCatagory> progCatList, String prognosticOtherText,
			List<DiagnoseCatagory> diagCatList, String diagOtherText,
			String noteFromPa, String noteFromDr,
			List<SurgeryCatagory> surList, String surgeryNote,
			Date surgeryDate, boolean complication, boolean beauty,
			String smallSurgery, String drAdvice, Date nextApp,
			AppointmentCatagory appPurpose, String appNote) {
		
		if (patientSelected == null) {
			throw new IllegalAccessError("Chưa chọn bệnh nhân");
		}

		MedicalPersonalInfo medicalPersonalInfo = patientSelected.getMedicalPersonalInfo();
		medicalPersonalInfo.getPatientCaseList()
								.updateCase(paCaseEntity, drCat, serviceList, progCatList, prognosticOtherText,
										diagCatList, diagOtherText, noteFromPa, noteFromDr, surList, surgeryNote,
										surgeryDate, complication, beauty, smallSurgery, drAdvice, nextApp,
										appPurpose, appNote);
		try {
			new PatientDao().update(patientSelected);
		
			AppointmentSchedule appSche = paCaseEntity.getAppoSchedule();
			if (appSche.getId() == null) {
				appSche.setPatientId(patientSelected.getId());
				new ScheduleDao().insert(appSche);
			} else {
				new ScheduleDao().update(appSche);
			}
		} catch (SQLException e) {
			throw new PamaException("Lổi cập nhật DB: " + e.getMessage());
		}
		getCurrentPatient().reloadMedicalInfo();
		notifyPaListener(patientSelected, patientSelected, paListeners);
	}

	/* (non-Javadoc)
	 * @see com.lehanh.pama.patientcase.IPatientManager#createEmptyCase(com.lehanh.pama.patientcase.PatientCaseStatus)
	 */
	@Override
	public void createEmptyCase(PatientCaseStatus status) {
		 ((PatientCaseList) getCurrentPatient().getMedicalPersonalInfo().getPatientCaseList()).createEmptyCase(status);
	}

	@Override
	public void cancelEditingPatientCase() {
		getCurrentPatient().reloadMedicalInfo();
	}

}
