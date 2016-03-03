package com.lehanh.pama.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.lehanh.pama.ICatagoryManager;
import com.lehanh.pama.IPatientManager;
import com.lehanh.pama.IService;
import com.lehanh.pama.PamaApplication;
import com.lehanh.pama.catagory.CatagoryManager;
import com.lehanh.pama.db.DatabaseManager;
import com.lehanh.pama.patientcase.PatientManager;

public class PamaHome {

	public static PamaApplication application;

	public static final String DB_NAME_PRO_NAME = "lh_pama_db_name";
	public static final String DB_USER_NAME_PRO_NAME = "lh_pama_user_name";
	public static final String DB_PASS_PRO_NAME = "lh_pama_pass";
	public static final String DB_IP_PRO_NAME = "lh_pama_db_ip";
	
	public static String getDbName() {
		return application.getProperty(DB_NAME_PRO_NAME);
	}

	public static String getDbUserName() {
		return application.getProperty(DB_USER_NAME_PRO_NAME);
	}

	public static String getDbPass() {
		return application.getProperty(DB_PASS_PRO_NAME);
	}

	public static String getDbIp() {
		return application.getProperty(DB_IP_PRO_NAME);
	}
	
	private static final Map<Class<?>, IService> serviceManager = new HashMap<Class<?>, IService>();
	static {
		serviceManager.put(ICatagoryManager.class, new CatagoryManager());
		serviceManager.put(IPatientManager.class, new PatientManager());
	}
	public static IService getService(Class<?> service) {
		return serviceManager.get(service);
	}

	public static void initialize() {
		try {
			DatabaseManager.initialize();
			for (IService service : serviceManager.values()) {
				service.initialize();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}